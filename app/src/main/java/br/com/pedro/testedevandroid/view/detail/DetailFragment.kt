package br.com.pedro.testedevandroid.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.pedro.testedevandroid.data.entity.CheckIn
import br.com.pedro.testedevandroid.data.entity.Event
import br.com.pedro.testedevandroid.databinding.FragmentDetailBinding
import br.com.pedro.testedevandroid.utils.Resource
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_list.*
import java.text.SimpleDateFormat

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.event.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindEvent(it.data!!)
                    binding.progressBar.visibility = View.GONE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(context, "Falha na conexão", Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun bindEvent(event: Event) {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(event.date)
        binding.title.text = event.title
        Glide.with(binding.root).load(event.image).into(binding.imageview)
        binding.date.text = dateString
        binding.description.text = event.description

        doCheckInButton(event.id)
    }

    private fun doCheckInButton(id : Int) {
        val nameCheckIn = binding.textFieldName.editText?.text.toString()
        val emailCheckIn = binding.textFieldEmail.editText?.text.toString()
        binding.button.setOnClickListener {
            viewModel.doCheckIn(CheckIn(id.toString(), nameCheckIn, emailCheckIn))
        }
    }


}