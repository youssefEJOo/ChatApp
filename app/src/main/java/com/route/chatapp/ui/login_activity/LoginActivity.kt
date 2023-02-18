package com.route.chatapp.ui.login_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.BaseActivity
import com.route.chatapp.databinding.ActivityLoginBinding
import com.route.chatapp.ui.home_activity.HomeActivity
import com.route.chatapp.ui.register_activity.RegisterActivity
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() , Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        subscribeToLiveData()

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
    }

    override fun getLayoutFile(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun navigateTo() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}