package com.verkada.android.catpictures.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.verkada.android.catpictures.data.Picture
import com.verkada.android.catpictures.theme.Purple200

const val COLUMNS = 3
const val PADDING_DP = 3
const val PICTURE_MIN_HEIGHT_DP = 150

/**
 * Represents the Grid of pictures to be shown
 *
 * picturesStateList - A SnapshotStateList of pictures to show
 * selectedPictureIndexState - Index of the selected picture in the picturesStateList, to be highlighted
 */
@Composable
fun PicturesGrid(picturesStateList: SnapshotStateList<Picture>, selectedPictureIndex: MutableState<Int>) {
    val pictures = remember { picturesStateList }
    var selectedIndex by remember { selectedPictureIndex }

    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMNS),
        verticalArrangement = Arrangement.spacedBy(PADDING_DP.dp),
        horizontalArrangement = Arrangement.spacedBy(PADDING_DP.dp),
        modifier = Modifier.padding(PADDING_DP.dp)
    ) {
        items(pictures.size) { index ->
            val picture = pictures[index]
            var colorFilter: ColorFilter? = null
            if (index == selectedIndex) {
                colorFilter = ColorFilter.tint(Purple200, BlendMode.Color)
            }
            AsyncImage(
                model = picture.url,
                contentDescription = picture.id,
                colorFilter = colorFilter,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.clickable {
                    if (selectedIndex == index) {
                        selectedIndex = -1
                    } else {
                        selectedIndex = index
                    }
                }.heightIn(min = PICTURE_MIN_HEIGHT_DP.dp)
            )
        }
    }
}

