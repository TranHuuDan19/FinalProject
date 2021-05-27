package com.example.bt1

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class IdolAdapter :RecyclerView.Adapter<IdolAdapter.ViewHoder>()  {
    var data : List<Idol> = listOf()
        set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder { // ket noi layout voi item
       var layoutInflater = LayoutInflater.from(parent.context)
       var view =  layoutInflater.inflate(R.layout.idol_item_view, parent, false)
        return ViewHoder(view)
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        var item = data[position]
        holder.tvname.text= item.name
        holder.tvsong.text = item.song
        holder.imavata.setImageResource(item.avatar)
    }

    override fun getItemCount(): Int {
        return  data.size
    }
// lop trung gian de lien ket voi layout
    class ViewHoder(var itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvname = itemView.findViewById<TextView>(R.id.text_name)
        val tvsong = itemView.findViewById<TextView>(R.id.text_song)
        val imavata = itemView.findViewById<ImageView>(R.id.imageView)
    }


}

