package com.gahee.myprography

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_item.view.*

class FilmsAdapter(
) :
    ListAdapter<Film, ListViewHolder>(FilmDiffUtilCallback()) {

    private lateinit var context : Context

    var films =  listOf<Film>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.view_holder_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(films[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(FILM_DATA_KEY, films[position])
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return films.size
    }

    companion object {
        const val FILM_DATA_KEY : String = "film_data_key"
    }

}

class FilmDiffUtilCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

}

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(item : Film){
        itemView.tv_main_film_title.text = item.title
        itemView.tv_main_film_director.text = item.director
        itemView.tv_main_film_release_date.text = item.releaseDate
    }


}
