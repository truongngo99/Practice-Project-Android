package com.example.practice_project_android.view.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Result
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val resultSearch = MutableLiveData<Result>()
    val isLoading = MutableLiveData<Boolean>()
    val failure = MutableLiveData<String>()

    fun searchMovie(key: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val repoSearch = repository.searchMovie(key)
            withContext(Dispatchers.Main) {
                try {
                    resultSearch.value = repoSearch
                    isLoading.value = false
                } catch (e: Exception) {
                    failure.value = e.toString()
                }
            }
        }
    }
}
