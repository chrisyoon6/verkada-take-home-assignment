package com.verkada.android.catpictures.data

import com.verkada.android.catpictures.R
import okhttp3.Route

sealed class BottomTabItem(val route: String, val resourceId: Int, val isSelected: Boolean) {
    object Home: BottomTabItem("Home", R.drawable.home, true)
    object Favorites: BottomTabItem("Favorites", R.drawable.fav_filled, false)
}