import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.api.RetrofitInstance
import com.example.practice_project_android.data.model.RequestToken
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    val myResponse: MutableLiveData<RequestToken> = MutableLiveData()
    val repository: Repository = Repository()
    fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            val response:RequestToken = repository.getRequestToken()
            withContext(Dispatchers.Main){
                myResponse.value = response
            }
        }
    }
}