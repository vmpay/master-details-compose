package eu.vmpay.weather.compose.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.vmpay.weather.compose.app.repository.Repository

abstract class BaseViewModel(protected val repository: Repository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()
}