package com.verkada.android.catpictures.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verkada.android.catpictures.data.Picture
import com.verkada.android.catpictures.network.PictureClient
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val pictureService = PictureClient.pictureService
    val selectedPictureIndexState = mutableStateOf<Int>(-1)
    var selectedFavoritePictureIndex = mutableStateOf<Int>(-1)
    var favoritePictures = mutableStateListOf<Picture>()
    var pictures = mutableStateListOf<Picture>()

    private val TAG = MainViewModel::class.java.simpleName

    init {
        updatePictures()
    }

    /**
     * Updates the list of pictures obtained
     */
    private fun updatePictures() {
        viewModelScope.launch {
            try {
                pictures.clear()
                pictureService.pictures().forEach {
                    pictures.add(it)
                }
            } catch(e : Exception) {
                // TODO: handle
                Log.d(TAG, e.toString())
            }
        }
    }
}