package com.example.practice_project_android.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.RequestToken
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(val repository: Repository): ViewModel() {
    val result  = MutableLiveData<RequestToken>()
    fun getToken(){
        viewModelScope.launch(Dispatchers.IO) {
           val myRequestToken = repository.getRequestToken()
            withContext(Dispatchers.Main){
                result.value = myRequestToken
            }

        }
    }
}