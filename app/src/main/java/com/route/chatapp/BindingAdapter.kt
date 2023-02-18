package com.route.chatapp

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setError(textInput : TextInputLayout , errorMessage : String?){
    textInput.error = errorMessage
}