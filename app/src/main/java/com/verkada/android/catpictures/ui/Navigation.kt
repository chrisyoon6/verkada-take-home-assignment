package com.verkada.android.catpictures.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.verkada.android.catpictures.data.BottomTabItem
import com.verkada.android.catpictures.theme.LightBlue
import com.verkada.android.catpictures.viewmodel.MainViewModel

@Composable
fun BottomTabNavigation(bottomTabItems: List<BottomTabItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedTabRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = LightBlue
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
                selected = bottomTabItem.route == selectedTabRoute,
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
fun NavigationHandler(viewModel: MainViewModel, navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = BottomTabItem.Home.route, modifier = modifier) {
        composable(BottomTabItem.Home.route) {
            HomeScreen(viewModel)
        }
        composable(BottomTabItem.Favorites.route) {
            FavoritesScreen(viewModel)
        }
    }
}