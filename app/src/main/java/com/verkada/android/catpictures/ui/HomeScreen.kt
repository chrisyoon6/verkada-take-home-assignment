package com.verkada.android.catpictures.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.verkada.android.catpictures.viewmodel.MainViewModel

/**
 * Contents shown when clicked on the Home tab. Shown first as default.
 */
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectedPicture(viewModel.pictures, viewModel.selectedPictureIndexState, viewModel)
        PicturesGrid(viewModel.pictures, viewModel.selectedPictureIndexState)
    }
}
