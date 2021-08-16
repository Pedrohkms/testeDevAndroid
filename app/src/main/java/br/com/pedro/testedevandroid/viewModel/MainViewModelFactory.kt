package br.com.pedro.testedevandroid.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.pedro.testedevandroid.ViewModel.MainViewModel
import br.com.pedro.testedevandroid.model.repository.EventRepository

class ViewModelFactory constructor(private val eventRepository: EventRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.eventRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}