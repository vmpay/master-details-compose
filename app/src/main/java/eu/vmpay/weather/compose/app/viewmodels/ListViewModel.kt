package eu.vmpay.weather.compose.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.vmpay.weather.compose.app.models.ItemModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ListViewModel @Inject constructor() : BaseViewModel() {
    val listLD = MutableLiveData<List<ItemModel>>()

    init {
        val newList = mutableListOf<ItemModel>()
        val random = Random(9999)
        for (i in 0 until 20) {
            val id = random.nextInt(999)
            newList.add(
                ItemModel(
                    "$id",
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "https://picsum.photos/id/$id/1200/600",
                    "Davenport, California $id",
                    1454130808_000
                )
            )
        }
        isLoading.value = true
        viewModelScope.launch {
            delay(1_000)
            isLoading.value = false
            listLD.value = newList
        }
    }
}
