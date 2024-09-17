package com.verkada.android.catpictures.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.verkada.android.catpictures.data.BottomTabItem
import com.verkada.android.catpictures.viewmodel.MainViewModel

@Composable
fun BottomTabNavigation(bottomTabItems: List<BottomTabItem>, navController: NavHostController) {
    val selectedTab by remember { mutableStateOf(BottomTabItem.Home) }

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        bottomTabItems.forEach { bottomTabItem ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(bottomTabItem.resourceId),
                        contentDescription = bottomTabItem.route,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(bottomTabItem.route) },
                selected = bottomTabItem.route == selectedTab.route,
                onClick = {
                    navController.navigate(bottomTabItem.route) {
                        // TODO
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                unselectedContentColor = Color.Gray,
                selectedContentColor = Color.Black
            )
        }
    }
}

@Composable
fun NavigationHandler(viewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = BottomTabItem.Home.route) {
        composable(BottomTabItem.Home.route) {
            HomeScreen(viewModel)
        }
    }
}