package edu.temple.activitystate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val imageId = MutableLiveData<Int>()

    fun getImageId() : LiveData<Int> {
        return imageId
    }

    fun setImageId(id: Int) {
        imageId.value = id
    }
}