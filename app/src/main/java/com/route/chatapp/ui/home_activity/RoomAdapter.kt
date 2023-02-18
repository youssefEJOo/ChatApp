package com.route.chatapp.ui.home_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.chatapp.R
import com.route.chatapp.models.Category
import com.route.chatapp.models.Room


class RoomAdapter(var rooms: MutableList<Room>?) : RecyclerView.Adapter<RoomAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = rooms?.get(position)
        val imageCategory = Category.getCategoryFromId(item!!.categoryId)
        holder.imageRoom.setImageResource(imageCategory.imageId)
        holder.titleRoom.text = item.title
        holder.itemView.setOnClickListener {it
            onRoomClick?.onRoomClicked(position , item)
        }
    }

    override fun getItemCount(): Int {
        return rooms?.size ?: 0
    }
    var onRoomClick: OnRoomClick? = null
    interface  OnRoomClick{
        fun onRoomClicked(position: Int , room : Room)

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageRoom = itemView.findViewById<ImageView>(R.id.image_room)
        val titleRoom = itemView.findViewById<TextView>(R.id.title_of_room)
    }

}