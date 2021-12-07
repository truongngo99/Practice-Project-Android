package com.example.practice_project_android.view.splash

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.authenticcation.RequestToken
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val result = MutableLiveData<RequestToken>()
    val failure = MutableLiveData<String>()
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun getToken() {
        viewModelScope.launch(Dispatchers.IO) {
            val myRequestToken = repository.getRequestToken()
            try {
                if (myRequestToken.success) {
                    val requestTokenRepository =
                        sharedPreferences.edit().putString("token", myRequestToken.request_token)
                            .commit()
                    withContext(Dispatchers.Main) {
                        result.value = myRequestToken
                    }
                }
            } catch (e: Exception) {
                failure.value = e.toString()
            }
        }
    }
}
