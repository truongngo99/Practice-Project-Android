package com.example.practice_project_android.view.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.authenticcation.LoginBody
import com.example.practice_project_android.data.model.authenticcation.RequestToken
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( private val repository: Repository)
    : ViewModel() {
     val result  = MutableLiveData<RequestToken>()
    val loginFailure = MutableLiveData<String?>()
    @Inject lateinit var sharedPreferences: SharedPreferences
    fun login(username : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            val token :String?  = sharedPreferences.getString("token", Context.MODE_PRIVATE.toString())

            val bodyLogin = LoginBody(username,password, token!!)
            try {
                val myLogin = repository.login(bodyLogin)
                if (myLogin.success){
                    withContext(Dispatchers.Main){
                        result.value = myLogin
                    }
                }
            } catch (e : Exception) {
                withContext(Dispatchers.Main){
                    loginFailure.value = e.message
                }
            }


        }
    }
}