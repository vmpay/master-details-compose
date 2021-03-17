package eu.vmpay.weather.compose.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.vmpay.weather.compose.app.models.ItemModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val listLD = MutableLiveData<List<ItemModel>>()

    private val list = listOf(
        ItemModel(
            "id1",
            "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw",
            "https://picsum.photos/300/300",
            "Davenport, California",
            1458220808_000
        ),
        ItemModel(
            "id2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "https://picsum.photos/300/300",
            "Davenport, California",
            1455130808_000
        ),
        ItemModel(
            "id3",
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "https://picsum.photos/300/300",
            "Davenport, California",
            1454130808_000
        ),
    )

    init {
//        listLD.value = list
        isLoading.value = true
        viewModelScope.launch {
            delay(5_000)
            isLoading.value = false
            listLD.value = list
        }
    }
}
