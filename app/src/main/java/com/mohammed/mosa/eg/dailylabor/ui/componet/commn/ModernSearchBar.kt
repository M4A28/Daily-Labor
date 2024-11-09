package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ModernSearchBar(
    onSearchChanged: (String) -> Unit = {},
    placeholderText: String = "Search...",
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimary,
    accentColor: Color = MaterialTheme.colorScheme.primary
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            // Search Icon
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = accentColor,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )

            // Text Field
            BasicTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onSearchChanged(it.text)
                },
                singleLine = true,
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (searchText.text.isEmpty()) {
                        Text(
                            text = placeholderText,
                            color = Color.Gray.copy(alpha = 0.5f)
                        )
                    }
                    innerTextField()
                }
            )

            // Clear Button
            if (searchText.text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .clickable {
                            searchText = TextFieldValue("")
                            onSearchChanged("")
                        }
                        .padding(4.dp)
                )
            }
        }
    }
}

// Example usage in a composable
@Preview
@Composable
fun SearchBarExample() {
    var searchResults by remember { mutableStateOf(listOf<String>()) }

    Column {
        ModernSearchBar(
            onSearchChanged = { query ->
                // Implement your search logic here
                searchResults = listOf("Result 1", "Result 2")
            }
        )

        // Display search results
        searchResults.forEach { result ->
            Text(text = result, modifier = Modifier.padding(8.dp))
        }
    }
}