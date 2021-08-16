package br.com.pedro.testedevandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.pedro.testedevandroid.databinding.FragmentDetailBinding
import br.com.pedro.testedevandroid.model.Event
import br.com.pedro.testedevandroid.viewModel.DetailViewModel
import kotlin.properties.Delegates

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var eventId by Delegates.notNull<Int>()
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventId = it.getInt("eventId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        bindMovie()
    }

    private fun bindMovie() {
        viewModel.event.observe(viewLifecycleOwner, {
            it.let {
                binding.oi.text = event.title
            }
        })
        viewModel.start(eventId)
    }
}