package br.com.pedro.testedevandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.pedro.testedevandroid.EventAdapter
import br.com.pedro.testedevandroid.ViewModel.MainViewModel
import br.com.pedro.testedevandroid.model.api.RetrofitService
import br.com.pedro.testedevandroid.databinding.ActivityMainBinding
import br.com.pedro.testedevandroid.model.repository.EventRepository
import br.com.pedro.testedevandroid.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = EventAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = EventRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.eventList.observe(this, {
            adapter.setEvents(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getEvents()

    }
}