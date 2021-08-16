package br.com.pedro.testedevandroid.view.events.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedro.testedevandroid.databinding.ItemListBinding
import br.com.pedro.testedevandroid.data.entity.Event
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class EventAdapter(private val listener: EventItemListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    var eventList = mutableListOf<Event>()

    interface EventItemListener {
        fun onClickedEvent(eventId: Int)
    }

    fun setItems(items: ArrayList<Event>) {
        this.eventList.clear()
        this.eventList.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEvents(events: List<Event>) {
        this.eventList = events.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(eventList[position])

    }

    override fun getItemCount(): Int {
        return eventList.size
    }


}

class MainViewHolder(
    val itemBinding: ItemListBinding,
    private val listener: EventAdapter.EventItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var event: Event

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Event) {
        this.event = item
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(event.date)
        itemBinding.title.text = event.title
        Glide.with(itemBinding.root).load(event.image).into(itemBinding.imageview)
        itemBinding.date.text = dateString

    }

    override fun onClick(v: View?) {
        listener.onClickedEvent(event.id)
    }

}