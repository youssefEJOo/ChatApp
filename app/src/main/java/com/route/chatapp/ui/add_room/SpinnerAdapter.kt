package com.route.chatapp.ui.add_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.route.chatapp.R
import com.route.chatapp.models.Category

class SpinnerAdapter (val items: List<Category>) : BaseAdapter()  {


    override fun getCount(): Int {
      return  items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var view = view
        var viewHolder: ViewHolder
        if (view == null) {
            view =
                LayoutInflater.from(parent?.context).inflate(R.layout.spinner_item, parent, false)
            viewHolder = ViewHolder(itemView = view)
            view.tag = viewHolder
        }else{
            viewHolder = view.tag as ViewHolder
        }
        val item = items.get(position)
        viewHolder.spinnerImage.setImageResource(item.imageId)
        viewHolder.spinnerTitle.text = item.title
        return view!!
    }

    class ViewHolder(val itemView: View){
        val spinnerImage = itemView.findViewById<ImageView>(R.id.image_spinner)
        val spinnerTitle = itemView.findViewById<TextView>(R.id.title_spinner)
    }
}