package com.revbase.zaidanarrafif.presentation.student.main_student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.BottomNavItem
import com.revbase.zaidanarrafif.presentation.ui.theme.DarkGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun BottomNavBar(
    items:List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem)->Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected ,
                onClick = { onItemClick(item) },
                selectedContentColor = Pink,
                unselectedContentColor = DarkGrey,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = if(selected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.name
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontFamily = Constant.LATO_FONT_FAMILY,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }
}