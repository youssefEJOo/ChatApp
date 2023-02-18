package com.route.chatapp.ui.register_activity

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatapp.firebase.addToFireStore
import com.route.chatapp.models.AddUser
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val firstNameError = ObservableField<String>()
    val lastNameError = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val progressLoadingLiveData = MutableLiveData<Boolean>()
    val massageLiveData = MutableLiveData<String>()
    var navigator : Navigator? = null
   val  auth = Firebase.auth

    fun createAccount(){
        viewModelScope.launch {
            if (validate()){
                progressLoadingLiveData.value = true
                auth.createUserWithEmailAndPassword(email.get()!! , password.get()!!).addOnCompleteListener { task->
                    progressLoadingLiveData.value = false
                    if (!task.isSuccessful){
                        progressLoadingLiveData.value = false
                        massageLiveData.value = task.exception!!.message

                    }else{
                        val users =  AddUser(Id = task.result.user?.uid,
                            firstName = firstName.get() , lastName = lastName.get(),
                            email = email.get())
                        addToFireStore(users , onSuccessListener = {
                            progressLoadingLiveData.value = false
                            navigator?.navigateToHome()

                        }) {
                            progressLoadingLiveData.value = false
                            massageLiveData.value = it.message

                        }

                    }
                }
            }
        }

    }
    fun validate(): Boolean {
        var valid = true
        if (firstName.get().isNullOrBlank()) {
            valid = false
            firstNameError.set("Please enter valid first name")
        } else {
            firstNameError.set(null)
        }
        if (lastName.get().isNullOrBlank()){
            valid = false
            lastNameError.set("Please enter valid last name")
        } else {
            lastNameError.set(null)
        }
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