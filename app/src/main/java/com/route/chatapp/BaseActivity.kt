package com.route.chatapp

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel , DB : ViewDataBinding> : AppCompatActivity(){

    lateinit var viewModel : VM
    lateinit var viewDataBinding : DB
    open lateinit var progressDialog:ProgressDialog




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this , getLayoutFile())
        viewModel = initViewModel()
        progressDialog = ProgressDialog(this)


    }

    abstract fun getLayoutFile(): Int
    abstract fun initViewModel(): VM

        fun showAlertDialog(context: Context , title: String  , message: String){
        val alertDialog = AlertDialog.Builder(context).setTitle(title).setMessage(message)
         .setPositiveButton(
               "Ok"
          ) { p0, p1 -> p0!!.dismiss() }.show()
        }
        fun showLoading(){
            progressDialog = ProgressDialog.show(this, "Loading", "")
            progressDialog.show()
        }
        fun dismissLoading(){
            progressDialog.dismiss()
        }


    }

