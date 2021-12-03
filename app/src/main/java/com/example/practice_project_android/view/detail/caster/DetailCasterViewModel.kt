package com.example.practice_project_android.view.detail.caster

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.PeopleCast
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailCasterViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val resultInfoCaster = MutableLiveData<PeopleCast>()
    val isLoading = MutableLiveData<Boolean>()
    private val failure = MutableLiveData<String>()
    fun getInfoCaster(castId: Int) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val infoCaster = repository.getInfoCaster(castId)
            try {
                withContext(Dispatchers.Main) {
                    resultInfoCaster.value = infoCaster
                    isLoading.value = false
                }
            } catch (e: Exception) {
                failure.value = e.toString()
            }
        }
    }
}