package br.com.pedro.testedevandroid.viewModel

import androidx.lifecycle.*
import br.com.pedro.testedevandroid.model.Event
import br.com.pedro.testedevandroid.model.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(
    private val repository: EventRepository
) : ViewModel() {
    private val _event = MutableLiveData<Event>()

    fun start(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _event.postValue(repository.getEvent(id))
        }
    }

    val event:MutableLiveData<Event> = _event
}