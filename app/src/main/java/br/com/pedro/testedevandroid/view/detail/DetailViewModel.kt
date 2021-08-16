package br.com.pedro.testedevandroid.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import br.com.pedro.testedevandroid.data.entity.Event
import br.com.pedro.testedevandroid.data.local.EventDao
import br.com.pedro.testedevandroid.data.repository.EventRepository
import br.com.pedro.testedevandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: EventRepository,
    private val dao: EventDao
) : ViewModel() {
    private val _id = MutableLiveData<Int>()

    private val _event = _id.switchMap { id ->
        repository.getEvent(id)
    }
    val event: LiveData<Resource<Event>> = _event

    fun start(id: Int) {
        _id.value = id
    }

}