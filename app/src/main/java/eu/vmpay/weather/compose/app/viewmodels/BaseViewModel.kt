package eu.vmpay.weather.compose.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
}