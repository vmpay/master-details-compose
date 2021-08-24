package eu.vmpay.weather.compose.app.viewmodels

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.vmpay.weather.compose.app.models.ItemDetailsModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : BaseViewModel() {
    val detailsLD = MutableLiveData<ItemDetailsModel>()

    fun getDetails(id: String?) {
        val itemDetailsModel = ItemDetailsModel(
            (id ?: 0).toString(),
            "Author",
            0,
            0,
            "url",
            "https://picsum.photos/id/${id ?: 0}/1200/600"
        )
        detailsLD.value = itemDetailsModel
    }
}
