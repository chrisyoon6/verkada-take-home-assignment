package com.verkada.android.catpictures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
fun HomeScreen(viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectedPicture(viewModel)
        PicturesGrid(viewModel)
    }
}

@Composable
fun SelectedPicture(viewModel: MainViewModel) {
    val selectedPicture by remember {
       viewModel.selectedPicture
    }
    val favorites = remember {
        viewModel.favoritePictures
    }
    selectedPicture?.let {
        Box(
            modifier = Modifier.fillMaxHeight(0.35f).fillMaxWidth()
        ) {
            AsyncImage(
                model = it.url,
                contentDescription = it.id,
                contentScale = ContentScale.Inside,
                modifier = Modifier.padding(horizontal = 60.dp, vertical = 24.dp).align(Alignment.Center)
            )
            val drawable = if (favorites.contains(it)) {
                R.drawable.fav_filled
            } else {
                R.drawable.fav_empty
            }
            Image(
                painterResource(drawable),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                        if (favorites.contains(it)) {
                            favorites.remove(it)
                            if (favorites.isEmpty() || viewModel.selectedFavoritePictureIndex.value == favorites.size) {
                                viewModel.selectedFavoritePictureIndex.value = -1
                            }
                        } else {
                            favorites.add(it)
                        }
                    }.padding(5.dp)
            )
        }
    }
}
@Composable
fun PicturesGrid(viewModel: MainViewModel) {
    val pictures by remember {
        viewModel.pictures
    }
    var selectedPicture by remember {
        viewModel.selectedPicture
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = Modifier.padding(3.dp)
    ) {
        items(pictures.size) { index ->
            val picture = pictures[index]
            var colorFilter: ColorFilter? = null
            if (selectedPicture == picture) {
                colorFilter = ColorFilter.tint(Purple200, BlendMode.Color)
            }
            AsyncImage(
                model = picture.url,
                contentDescription = picture.id,
                colorFilter = colorFilter,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.clickable {
                    if (selectedPicture == picture) {
                        selectedPicture = null
                    } else {
                        selectedPicture = picture
                    }
                }.heightIn(min = 150.dp)
            )
        }
    }
}