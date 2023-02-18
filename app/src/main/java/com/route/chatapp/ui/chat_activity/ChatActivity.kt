package com.route.chatapp.ui.chat_activity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.route.chatapp.R
import com.route.chatapp.BaseActivity
import com.route.chatapp.databinding.ActivityChatBinding
import com.route.chatapp.models.Message
import com.route.chatapp.models.Room

class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding >()  {
    var  messagesList  = mutableListOf<Message>()
    var adapter : MessagesAdapter = MessagesAdapter(messagesList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        initViews()
        val roomId = intent.getStringExtra("roomId")
        viewModel.roomId = roomId
        subscribeToLiveData()
        subscribeToMessageCollection()
    }


    private fun subscribeToLiveData() {
        viewModel.progressLoadingLiveData.observe(this) {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        }
        viewModel.massageLiveData.observe(this) { message ->
            showAlertDialog(this, "Error" , message)
        }
        viewModel.setEditTextNull.observe(this){
            if (it == true){
            viewDataBinding.messageTi.editText?.text = null
            }
        }
    }
    fun initViews(){
        viewDataBinding.messageRecyclerview.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false  )
    viewDataBinding.messageRecyclerview.adapter = adapter

    }
    fun subscribeToMessageCollection(){
        var fireStore = Firebase.firestore
        fireStore.collection(Room.COLLECTION_NAME).document(viewModel.roomId!!)
            .collection(Message.COLLECTION_NAME).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("subscribeToMessageCollection", "Listen failed.", e)
                showAlertDialog(this , "Error" , "please try again")
                return@addSnapshotListener
            }

            if (snapshot != null ) {
              for (docChange in snapshot.documentChanges) {
                  when (docChange.type) {
                      DocumentChange.Type.ADDED -> {
                        val message =  docChange.document.toObject(Message::class.java)
                        messagesList.add(message)
                      }
                      else -> {
                          Log.e("subscribeToMessageCollection" , "Current data : null")
                      }
                  }
              }
                adapter.notifyDataSetChanged()
            } else {
            Log.e("subscribeToMessageCollection" , "Current data : null")
            }
        }

    }



    override fun getLayoutFile(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]
    }



}