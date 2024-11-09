package com.mohammed.mosa.eg.dailylabor.ui.componet.commn.bottomnavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGray200
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGray800
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsSecondary

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier
            //.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            //.background(MaterialTheme.colorScheme.surface)
            //.border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ,
        containerColor = White,

        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index ,item ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(item.icon), contentDescription = null)

                },
                label = {
                    Text(text = stringResource(item.title))
                },
                //alwaysShowLabel = false,

                colors = NavigationBarItemDefaults.colors(selectedIconColor = BsPrimary,
                    selectedTextColor = BsSecondary,
                    indicatorColor = BsGray200,
                    unselectedIconColor = BsGray800,
                    unselectedTextColor = BsGray800,


                    ),
                selected =  index == selectedItem,
                onClick = {
                        onItemClick(index)
                }
            )
        }
    }
}


@Preview
@Composable
private fun BottomNavigationBarPreview() {

    val items = listOf(
        NavigationItem.Home, // 1
        NavigationItem.Labor, //2
        NavigationItem.Payment,// 3
        NavigationItem.Setting // 4
    )
    BottomNavigationBar(items = items, selectedItem = 0, onItemClick = {})


}