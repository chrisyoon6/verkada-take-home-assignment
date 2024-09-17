package com.verkada.android.catpictures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.verkada.android.catpictures.R
import com.verkada.android.catpictures.theme.Purple200
import com.verkada.android.catpictures.viewmodel.MainViewModel

@Composable
fun FavoritesScreen(viewModel: MainViewModel) {
    val selectedFavoritePictureIndex by remember { viewModel.selectedFavoritePictureIndex }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedFavoritePictureIndex >= 0) {
            SelectedFavoritePicture(viewModel)
        }
        FavoritePicturesGrid(viewModel)
    }
}

@Composable
fun SelectedFavoritePicture(viewModel: MainViewModel) {
    val favorites = remember { viewModel.favoritePictures}
    var selectedFavoritePictureIndex by remember { viewModel.selectedFavoritePictureIndex }
    val selectedFavorite = favorites[selectedFavoritePictureIndex]
    Box(
        modifier = Modifier
            .fillMaxHeight(0.35f).fillMaxWidth()
    ) {
        AsyncImage(
            model = selectedFavorite.url,
            contentDescription = selectedFavorite.id,
            contentScale = ContentScale.Inside,
            modifier = Modifier.padding(horizontal = 60.dp, vertical = 24.dp).align(Alignment.Center)
        )
        Image(
            painterResource(R.drawable.fav_filled),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {
                    if (favorites.contains(selectedFavorite)) {
                        favorites.remove(selectedFavorite)
                    }
                    if (favorites.isEmpty() || selectedFavoritePictureIndex == favorites.size) {
                        selectedFavoritePictureIndex = -1
                    }
                }.padding(5.dp)
        )
    }
}

@Composable
fun FavoritePicturesGrid(viewModel: MainViewModel) {
    val favorites = remember { viewModel.favoritePictures}
    var selectedFavoritePictureIndex by remember { viewModel.selectedFavoritePictureIndex }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = Modifier.padding(3.dp)
    ) {
        items(favorites.size) { index ->
            val favoritePicture = favorites[index]
            var colorFilter: ColorFilter? = null
            if (selectedFavoritePictureIndex == index) {
                colorFilter = ColorFilter.tint(Purple200, BlendMode.Color)
            }
            AsyncImage(
                model = favoritePicture.url,
                contentDescription = favoritePicture.id,
                colorFilter = colorFilter,
                modifier = Modifier
                    .clickable {
                        if (selectedFavoritePictureIndex == index) {
                            selectedFavoritePictureIndex = -1
                        } else {
                            selectedFavoritePictureIndex = index
                        }
                    }.heightIn(max = 150.dp)
            )
        }
    }
}