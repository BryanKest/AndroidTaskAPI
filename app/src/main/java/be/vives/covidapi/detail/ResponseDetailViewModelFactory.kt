package be.vives.covidapi.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.vives.covidapi.model.Response

class ResponseDetailViewModelFactory(private val response: Response) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResponseDetailViewModel::class.java)) {
            return ResponseDetailViewModel(response) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}