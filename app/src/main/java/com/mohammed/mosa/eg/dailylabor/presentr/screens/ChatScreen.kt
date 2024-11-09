package com.mohammed.mosa.eg.dailylabor.presentr.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

data class Message(
    val id: Int,
    val content: String,
    val timestamp: Date,
    val isMe: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatScreen() {
    val messages = remember {
        mutableStateOf(listOf(
            Message(1, "Hey! How are you?", Date(System.currentTimeMillis() - 86400000), false),
            Message(2, "I'm good, thanks! How about you?", Date(System.currentTimeMillis() - 82800000), true),
            Message(3, "Let's meet tomorrow", Date(), false)
        ))
    }

    var messageText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        TopAppBar(
            title = { Text("Chat") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            )
        )

        // Messages
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            MessageList(messages = messages.value)
        }

        // Input
        ChatInput(
            value = messageText,
            onValueChange = { messageText = it },
            onSendClick = {
                if (messageText.isNotBlank()) {
                    val newMessage = Message(
                        id = messages.value.size + 1,
                        content = messageText,
                        timestamp = Date(),
                        isMe = true
                    )
                    messages.value = messages.value + newMessage
                    messageText = ""
                }
            }
        )
    }
}

@Composable
fun MessageList(messages: List<Message>) {
    val groupedMessages = messages.groupBy {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.timestamp)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        groupedMessages.forEach { (date, messagesForDate) ->
            item {
                DateDivider(date = date)
            }

            items(messagesForDate) { message ->
                MessageBubble(message = message)
            }
        }
    }
}

@Composable
fun DateDivider(date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            color = Color.Gray.copy(alpha = 0.3f)
        )
        Text(
            text = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
                .format(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)!!),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Divider(
            modifier = Modifier.weight(1f),
            color = Color.Gray.copy(alpha = 0.3f)
        )
    }
}

@Composable
fun MessageBubble(message: Message) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isMe) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (message.isMe) 16.dp else 0.dp,
                        bottomEnd = if (message.isMe) 0.dp else 16.dp
                    )
                )
                .background(
                    if (message.isMe) MaterialTheme.colorScheme.primary
                    else Color.LightGray.copy(alpha = 0.3f)
                )
                .padding(12.dp)
                .widthIn(max = 280.dp)
        ) {
            Column {
                Text(
                    text = message.content,
                    color = if (message.isMe) Color.White else Color.Black
                )
                Text(
                    text = SimpleDateFormat("HH:mm", Locale.getDefault())
                        .format(message.timestamp),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (message.isMe) Color.White.copy(alpha = 0.7f)
                    else Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun ChatInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Type a message...") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.LightGray.copy(alpha = 0.1f),
                    focusedContainerColor = Color.LightGray.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(24.dp)
            )

            Button(
                onClick = onSendClick,
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Send")
            }
        }
    }
}