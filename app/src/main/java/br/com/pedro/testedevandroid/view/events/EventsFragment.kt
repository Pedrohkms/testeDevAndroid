package br.com.pedro.testedevandroid.view.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedro.testedevandroid.R
import br.com.pedro.testedevandroid.databinding.FragmentEventsBinding
import br.com.pedro.testedevandroid.utils.Resource
import br.com.pedro.testedevandroid.view.events.adapter.EventAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment(), EventAdapter.EventItemListener {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: EventsViewModel by viewModels()
    private lateinit var adapter: EventAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    fun setupRecyclerView() {
        adapter = EventAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = adapter
    }

    fun setupObservers() {
        viewModel.events.observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }


    override fun onClickedEvent(eventId: Int) {
        findNavController().navigate(
            R.id.action_eventsFragment_to_detail_fragment,
            bundleOf("id" to eventId)
        )
    }

}