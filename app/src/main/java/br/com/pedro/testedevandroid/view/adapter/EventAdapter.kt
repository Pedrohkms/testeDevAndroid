package br.com.pedro.testedevandroid

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedro.testedevandroid.databinding.ItemListBinding
import br.com.pedro.testedevandroid.model.Event
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class EventAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var eventList = mutableListOf<Event>()

    @SuppressLint("NotifyDataSetChanged")
    fun setEvents(events: List<Event>) {
        this.eventList = events.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val event = eventList[position]
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(event.date)
        holder.binding.title.text = event.title
        Glide.with(holder.itemView.context).load(event.image).into(holder.binding.imageview)
        holder.binding.date.text = dateString

    }

    override fun getItemCount(): Int {
        return eventList.size
    }


}

class MainViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

}