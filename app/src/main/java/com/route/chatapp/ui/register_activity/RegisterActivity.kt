package com.route.chatapp.ui.register_activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R

import com.route.chatapp.BaseActivity
import com.route.chatapp.databinding.ActivityRegisterBinding
import com.route.chatapp.ui.home_activity.HomeActivity

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() {
        viewModel.progressLoadingLiveData.observe(this){
            if(it){
                showLoading()
            }else{
                dismissLoading()
            }
        }
        viewModel.massageLiveData.observe(this){message ->
            showAlertDialog(this , "Error" , message)

        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun navigateToHome() {
        val intent = Intent(this , HomeActivity::class.java)
        startActivity(intent)
    }
}