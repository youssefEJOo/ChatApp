package com.route.chatapp.ui.add_room

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.chatapp.firebase.addRoomToFireStore
import com.route.chatapp.models.Room

class AddRoomViewModel : ViewModel() {

    var title = ObservableField<String>()
    var titleError = ObservableField<String>()
    var description = ObservableField<String>()
    var descriptionError = ObservableField<String>()
    val progressLoadingLiveData = MutableLiveData<Boolean>()
    val massageLiveData = MutableLiveData<String>()
    var selectedCategoryId = "Sports"
    var navigator: Navigator? = null

    fun createRoom(){
    if (!validate()) return
        progressLoadingLiveData.value = true
        var room = Room(
            title = title.get()!!,
            description = description.get()!!,
            categoryId = selectedCategoryId
        )
        addRoomToFireStore(room , {
                progressLoadingLiveData.value = false
           // navigator?.navigateToHome()
            navigator?.endActivity()

        },{
            progressLoadingLiveData.value = false
            massageLiveData.value =it.message
        })
    }

    fun validate() : Boolean{
        var valid  = true
        if (title.get().isNullOrBlank()) {
            valid = false
            titleError.set("Please enter the name of room")
        }else{
            titleError.set(null)
        }
        if (description.get().isNullOrBlank()) {
            valid = false
            descriptionError.set("Please enter the name of room")
        }else{
            descriptionError.set(null)
        }
        return valid
    }

    }



