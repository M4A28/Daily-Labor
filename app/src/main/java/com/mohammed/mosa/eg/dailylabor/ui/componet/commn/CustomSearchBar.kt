package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryLighter


@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(28.dp),
        color = White,
        border = BorderStroke(1.dp, BsPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = {
                    onClearQuery()
                    focusManager.clearFocus()
                },

            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = BsPrimary
                )

            }

            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent),
                textStyle = TextStyle(
                    color = BsPrimaryDark,
                    fontSize = 16.sp
                ),
                singleLine = true,
                cursorBrush = SolidColor(BsPrimaryLighter),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    }
                )
            )

            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search",
                        tint = BsPrimaryLighter
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    title: String,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onCloseSearch: () -> Unit,
    isSearchActive: Boolean,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {}
) {
    val density = LocalDensity.current
    val statusBarHeight = WindowInsets.statusBars.getTop(density)

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = BsPrimary,
        contentColor = Color.White
    ) {
        Box(modifier = Modifier.padding(top = with(density) { statusBarHeight.toDp() })) {
            AnimatedVisibility(
                visible = !isSearchActive,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                CenterAlignedTopAppBar(
                    title = { Text(title, fontWeight = FontWeight.Bold, color = White) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BsPrimaryDark,
                        titleContentColor = White,
                        navigationIconContentColor = White,
                        actionIconContentColor = White
                    ),

                    actions ={
                        actions()
                    },
                    navigationIcon = {
                        IconButton(onClick = onSearchClick) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(
                visible = isSearchActive,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { it }),
                exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it })
            ) {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BsPrimaryDark,
                        titleContentColor = White,
                        navigationIconContentColor = White,
                        actionIconContentColor = White
                    ),
                    actions = {
                        CustomSearchBar(
                            query = query,
                            onQueryChange = onQueryChange,
                            onClearQuery = {
                                onCloseSearch()
                                onQueryChange("")
                            }
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PrevoewTopAppBar(){
    SearchTopAppBar(
        title = "Daily Labor",
        query = "",
        onCloseSearch = {},
        onSearchClick = {},
        onQueryChange = {},
        isSearchActive = false,
    )
}
