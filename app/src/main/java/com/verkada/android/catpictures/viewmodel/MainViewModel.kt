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
    val pictures = mutableStateOf<List<Picture>>(emptyList())
    val selectedPicture = mutableStateOf<Picture?>(null)
    var favoritePictures = mutableStateListOf<Picture>()
    var selectedFavoritePictureIndex = mutableStateOf<Int>(-1)

    private val TAG = MainViewModel::class.java.simpleName

    init {
        updatePictures()
    }

    fun updatePictures() {
        viewModelScope.launch {
            try {
                pictures.value = pictureService.pictures()
            } catch(e : Exception) {
                // TODO: handle
                Log.d(TAG, e.toString())
            }
        }
    }
}