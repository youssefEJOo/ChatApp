package com.route.chatapp.ui.chat_activity

import android.text.BoringLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.route.chatapp.Data
import com.route.chatapp.firebase.addMessageToFireStore
import com.route.chatapp.models.Message
import kotlin.time.Duration.Companion.hours

class ChatViewModel : ViewModel() {

    val progressLoadingLiveData = MutableLiveData<Boolean>()
    val massageLiveData = MutableLiveData<String>()
    val messageContent = MutableLiveData<String>()
    val setEditTextNull = MutableLiveData<Boolean>()
    var roomId : String ? = ""

    fun sendMessages(){
        if (messageContent.value.isNullOrBlank())return
        var message = Message(
            senderId = Data.currentUser!!.Id!! ,
            senderName = Data.currentUser!!.firstName!! ,
            messageContent = messageContent.value!!,
            time = Timestamp.now()

        )
        addMessageToFireStore(
            roomId = roomId?:"",
            message ,
            {
                setEditTextNull.value = true

            },{
                setEditTextNull.value = true
                progressLoadingLiveData.value = false
                massageLiveData.value = it.message
            }

        )


    }

    }



