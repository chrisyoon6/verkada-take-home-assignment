package com.verkada.android.catpictures

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.verkada.android.catpictures.data.BottomTabItem
import com.verkada.android.catpictures.theme.CatPicturesTheme
import com.verkada.android.catpictures.ui.BottomTabNavigation
import com.verkada.android.catpictures.ui.NavigationHandler
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
            CatPicturesTheme {
                Scaffold(
                    bottomBar = { BottomTabNavigation(bottomTabItems, navController)}
                ) { padding->
                    NavigationHandler(viewModel, navController, Modifier.padding(padding))
                }
            }
        }
    }
}
