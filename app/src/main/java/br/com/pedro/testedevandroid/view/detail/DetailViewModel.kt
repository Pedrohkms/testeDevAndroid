package br.com.pedro.testedevandroid.view.detail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import br.com.pedro.testedevandroid.data.entity.CheckIn
import br.com.pedro.testedevandroid.data.entity.Event
import br.com.pedro.testedevandroid.data.local.EventDao
import br.com.pedro.testedevandroid.data.repository.CheckInRepository
import br.com.pedro.testedevandroid.data.repository.EventRepository
import br.com.pedro.testedevandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val repository: EventRepository,
    private val repositoryCheckIn: CheckInRepository
) : ViewModel() {
    private val context = application
    private val _id = MutableLiveData<Int>()

    private val _event = _id.switchMap { id ->
        repository.getEvent(id)
    }
    val event: LiveData<Resource<Event>> = _event

    fun start(id: Int) {
        _id.value = id
    }

    fun doCheckIn(body: CheckIn) {
        viewModelScope.launch(Dispatchers.IO) {
            val check = repositoryCheckIn.checkIn(body)
            viewModelScope.launch {
                Toast.makeText(context, "Parabéns, CheckIn Realizado", Toast.LENGTH_LONG).show()
            }
        }
    }

}