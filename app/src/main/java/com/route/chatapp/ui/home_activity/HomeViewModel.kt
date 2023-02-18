package com.route.chatapp.ui.home_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.chatapp.firebase.getAllRooms
import com.route.chatapp.models.Room

class HomeViewModel : ViewModel() {
    val progressLoadingLiveData = MutableLiveData<Boolean>()
    val massageLiveData = MutableLiveData<String>()
    val roomList = MutableLiveData<Room>()
    var navigator : Navigator? = null


    fun getRooms(){
        getAllRooms({
            progressLoadingLiveData.value = false
           // dismissLoading()
            it.documents.forEach { doc->
                val room = doc.toObject(Room::class.java)
                roomList.value = room!!
               // roomList.add(room!!)
            }
            //adapter.notifyDataSetChanged()

        } , {
            progressLoadingLiveData.value = false
            //dismissLoading()
            massageLiveData.value = it.message
            //showAlertDialog(this , "Error" , it.message?:"")
        })
    }

    fun navigateToAddRoom(){
        navigator?.navigateToAddRoom()
    }

    }



