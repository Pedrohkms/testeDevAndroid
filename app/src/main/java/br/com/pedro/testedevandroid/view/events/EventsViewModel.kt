package br.com.pedro.testedevandroid.view.events

import androidx.lifecycle.ViewModel
import br.com.pedro.testedevandroid.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {

    val events = eventRepository.getEvents()

}