package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.jar.Attributes

class RestaurantAdapter:RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    var data:List<Song> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    //class viewHolder
    class ViewHolder(val itemView:View, val song_title: Any = itemView.findViewById<TextView>(R.id.song_title), val song_artist: Any = itemView.findViewById<TextView>(R.id.song_artist), val imageView: Any = itemView.findViewById<ImageView>(R.id.imageView)):RecyclerView.ViewHolder(itemView){

        companion object {
            fun from(parent: ViewGroup):ViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.activity_main,parent,false)
                return ViewHolder(view)
            }
        }
        fun bind(item: Song) {
            song_title.text = item.Name
            song_artist.text = item.artist
            Glide.with(itemView.context).load(item.picturePath).into(imageView)
        }
    }
}
