package com.route.chatapp.ui.chat_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.chatapp.Data
import com.route.chatapp.R
import com.route.chatapp.databinding.ItemMessageReciveBinding
import com.route.chatapp.databinding.ItemMessageSentBinding
import com.route.chatapp.models.Message

class MessagesAdapter(var items : MutableList<Message>) : RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>(){


    override fun getItemViewType(position: Int): Int {
        val message = items[position]
        if (message.senderId == Data.currentUser?.Id){
            return 0
        }else{
            return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        if (viewType == 0 ){
            val binding : ItemMessageSentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context) ,
            R.layout.item_message_sent , parent , false
                )
            return SentMessageViewHolder(binding)
        }else{
            val binding : ItemMessageReciveBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context) ,
                R.layout.item_message_recive , parent , false
            )
            return ReceivedMessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val message = items.get(position)
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract class MessagesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        abstract fun bind(message : Message)
    }
    class SentMessageViewHolder(val binding : ItemMessageSentBinding):MessagesViewHolder(binding.root) {
        override fun bind(message: Message) {
            binding.message = message
        }

    }
    class ReceivedMessageViewHolder(val binding : ItemMessageReciveBinding):MessagesViewHolder(binding.root) {
        override fun bind(message: Message) {
            binding.message = message
        }
    }
}