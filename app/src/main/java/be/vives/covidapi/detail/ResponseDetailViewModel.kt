package be.vives.covidapi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.vives.covidapi.model.Response

class ResponseDetailViewModel(val __response: Response) : ViewModel() {
    private var _response = MutableLiveData<Response>()
    val response : LiveData<Response>
    get() {
        return _response
    }

    init {
        _response.value = __response
    }

}