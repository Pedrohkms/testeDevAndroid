package br.com.pedro.testedevandroid.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pedro.testedevandroid.model.Event
import br.com.pedro.testedevandroid.model.repository.EventRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val eventRepository: EventRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val eventList = MutableLiveData<List<Event>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getEvents() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = eventRepository.getEvents()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    eventList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}