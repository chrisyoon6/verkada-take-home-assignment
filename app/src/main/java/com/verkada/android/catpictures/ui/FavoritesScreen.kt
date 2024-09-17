package com.verkada.android.catpictures.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.verkada.android.catpictures.viewmodel.MainViewModel

/**
 * Contents shown when clicked on the Favorites tab
 */
@Composable
fun FavoritesScreen(viewModel: MainViewModel) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectedPicture(viewModel.favoritePictures, viewModel.selectedFavoritePictureIndex, viewModel)
        PicturesGrid(viewModel.favoritePictures, viewModel.selectedFavoritePictureIndex)
    }
}
