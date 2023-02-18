package com.route.chatapp.models

import com.google.firebase.Timestamp

data class Message(

    var id : String? = "" ,
    val senderId : String = "" ,
    val senderName : String = "",
    val messageContent : String = "",
    val time : Timestamp = Timestamp.now()
){
    companion object{
        const val COLLECTION_NAME = "messages"
    }
}
