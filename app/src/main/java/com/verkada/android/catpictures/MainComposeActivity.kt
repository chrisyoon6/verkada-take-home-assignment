package com.verkada.android.catpictures

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.verkada.android.catpictures.data.BottomTabItem
import com.verkada.android.catpictures.theme.CatPicturesTheme
import com.verkada.android.catpictures.ui.BottomTabNavigation
import com.verkada.android.catpictures.ui.HomeScreen
import com.verkada.android.catpictures.ui.NavigationHandler
import com.verkada.android.catpictures.ui.PicturesGrid
import com.verkada.android.catpictures.ui.SelectedPicture
import com.verkada.android.catpictures.viewmodel.MainViewModel

class MainComposeActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val bottomTabItems = listOf(
                BottomTabItem.Home,
                BottomTabItem.Favorites
            )
            Scaffold(
                bottomBar = { BottomTabNavigation(bottomTabItems, navController)}
            ) {
                NavigationHandler(viewModel, navController)
            }
//            HomeScreen(viewModel)
        }
    }
}
