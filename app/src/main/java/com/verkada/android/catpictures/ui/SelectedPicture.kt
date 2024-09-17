package com.verkada.android.catpictures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.verkada.android.catpictures.R
import com.verkada.android.catpictures.data.Picture
import com.verkada.android.catpictures.viewmodel.MainViewModel

const val MAX_HEIGHT_FRACTION = 0.35f
const val PICTURE_HORIZONTAL_PADDING_DP = 60
const val PICTURE_VERTICAL_PADDING_DP = 24
const val FAV_ICON_PADDING_DP = 5

/**
 * Displays an enlarged version of the selected picture
 *
 * picturesStateList - A SnapshotStateList of pictures that the selected picture is from
 * selectedPictureIndexState - Index of the selected picture in the picturesStateList
 * viewModel - The main ViewModel
 */
@Composable
fun SelectedPicture(picturesStateList: SnapshotStateList<Picture>, selectedPictureIndexState: MutableState<Int>, viewModel: MainViewModel) {
    val selectedIndex by remember {
        selectedPictureIndexState
    }
    val pictures = remember {
        picturesStateList
    }
    val selectedPicture = if (selectedIndex >= 0) pictures[selectedIndex] else null
    selectedPicture?.let {
        Box(
            modifier = Modifier.fillMaxHeight(MAX_HEIGHT_FRACTION).fillMaxWidth()
        ) {
            AsyncImage(
                model = it.url,
                contentDescription = it.id,
                contentScale = ContentScale.Inside,
                modifier = Modifier.padding(
                    horizontal = PICTURE_HORIZONTAL_PADDING_DP.dp,
                    vertical = PICTURE_VERTICAL_PADDING_DP.dp
                ).align(Alignment.Center)
            )
            val drawable = if (viewModel.favoritePictures.contains(it)) {
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
                        if (viewModel.favoritePictures.contains(it)) {
                            viewModel.favoritePictures.remove(it)
                            if (viewModel.favoritePictures.isEmpty() || viewModel.selectedFavoritePictureIndex.value == viewModel.favoritePictures.size) {
                                viewModel.selectedFavoritePictureIndex.value = -1
                            }
                        } else {
                            viewModel.favoritePictures.add(it)
                        }
                    }.padding(FAV_ICON_PADDING_DP.dp)
            )
        }
    }
}
