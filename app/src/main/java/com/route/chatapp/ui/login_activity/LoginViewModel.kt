package com.route.chatapp.ui.login_activity

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatapp.Data
import com.route.chatapp.firebase.getUserFromFireStore
import com.route.chatapp.models.AddUser


class LoginViewModel : ViewModel() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val progressLoadingLiveData = MutableLiveData<Boolean>()
    val massageLiveData = MutableLiveData<String>()
    val  auth = Firebase.auth
    var navigator : Navigator? = null

    fun navigateToRegisterScreen(){
        navigator?.navigateToRegister()
    }
    fun login(){
        if (validate()){
            progressLoadingLiveData.value = true
        auth.signInWithEmailAndPassword(email.get()!! , password.get()!!).addOnCompleteListener { task->
            progressLoadingLiveData.value = false
            if (!task.isSuccessful){
                Log.e("login" , task.exception!!.message.toString())
                massageLiveData.value = task.exception!!.message

            }else{
                    getUserFromFireStore(task.result.user?.uid?:"" , success = {docSnapShot->
                        val currentUser = docSnapShot.toObject(AddUser::class.java)
                        Data.currentUser = currentUser
                        navigator?.navigateTo()


                    }) {
                        progressLoadingLiveData.value = false
                        massageLiveData.value = it.message
                    }
            }
        }
        }

    }

    fun validate(): Boolean {
        var valid = true

        if (email.get().isNullOrBlank()){
            valid = false
            emailError.set("Please enter valid first email")
        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()){
            valid = false
            passwordError.set("Please enter valid password")
        } else {
            passwordError.set(null)
        }
        return valid
    }


}