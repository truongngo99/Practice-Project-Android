package com.example.practice_project_android.view.detail.poster

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Images
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailPosterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var resultImage = MutableLiveData<Images>()
    val failure = MutableLiveData<String>()
     fun getImages(movieId:Int){
       viewModelScope.launch(Dispatchers.IO) {
           val listImage = repository.getImages(movieId)
           try {
               withContext(Dispatchers.Main){
                   resultImage.value = listImage
               }
           } catch (e:Exception){
               failure.value = e.toString()
           }
       }
    }
}