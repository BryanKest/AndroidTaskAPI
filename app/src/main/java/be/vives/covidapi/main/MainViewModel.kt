package be.vives.covidapi.main

import be.vives.covidapi.model.CovidBase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.vives.covidapi.model.Response
import be.vives.covidapi.network.CovidApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _covidBase = MutableLiveData<CovidBase?>()
    val covidBase: LiveData<CovidBase?>
    get() {
        return _covidBase
    }

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
    get() {
        return _error
    }

    private var _loadingFinished = MutableLiveData<Boolean>()
    val loadingFinished : LiveData<Boolean>
    get() {
        return _loadingFinished
    }
    private var _response = MutableLiveData<Response?>()
    val response: LiveData<Response?>
    get() {
        return _response
    }

    init {
        _error.value = ""
        _loadingFinished.value = false
        viewModelScope.launch {
            try {
                _covidBase.value  = CovidApi.retrofitService.getHistoryByCountry("Belgium")
                _loadingFinished.value = true
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true
                //print(e.localizedMessage)
            }
        }
    }

    fun onResponseClicked(response: Response) {
        _response.value = response
    }

    fun navigateToDetailFinished() {
        _response.value = null
    }

}