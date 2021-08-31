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
class DetailsViewModel @Inject constructor(repository: Repository) : BaseViewModel(repository) {
    val detailsLD = MutableLiveData<ItemDetailsModel>()

    fun getDetails(id: String?) {
        detailsLD.value = null
        isError.value = null
        if (id.isNullOrBlank()) {
            isError.value = "ID is null or blank"
        } else {
            isLoading.value = true
            viewModelScope.launch {
                try {
                    val itemDetailsModel = repository.getPictureDetails(id)
                    detailsLD.value = itemDetailsModel
                } catch (exception: Exception) {
                    Log.e("ListViewModel", "IOException $exception")
                    isError.value = "IOException $exception"
                }
                isLoading.value = false
            }
        }
    }
}
