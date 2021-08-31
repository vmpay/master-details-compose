package eu.vmpay.weather.compose.app.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.vmpay.weather.compose.app.models.ItemDetailsModel
import eu.vmpay.weather.compose.app.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(repository: Repository) : BaseViewModel(repository) {
    val listLD = MutableLiveData<List<ItemDetailsModel>>()

    init {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val list = repository.getPictureList()
                listLD.value = list
            } catch (exception: Exception) {
                Log.e("ListViewModel", "IOException $exception")
                isError.value = "IOException $exception"
            }
            isLoading.value = false
        }
    }
}
