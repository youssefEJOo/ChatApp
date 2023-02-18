package com.route.chatapp.ui.add_room
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.BaseActivity
import com.route.chatapp.databinding.ActivityAddRoomBinding
import com.route.chatapp.models.Category
import com.route.chatapp.ui.home_activity.HomeActivity

class AddRoomActivity : BaseActivity<AddRoomViewModel, ActivityAddRoomBinding >() , Navigator {
        var adapter: SpinnerAdapter = SpinnerAdapter(Category.Categories)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.actionBarSpinner.adapter = adapter
        viewModel.navigator = this
        subscribeToLiveData()
        progressDialog = ProgressDialog(this)

        viewDataBinding.actionBarSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedCategoryId = Category.Categories.get(position).id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }


    fun subscribeToLiveData(){
        viewModel.progressLoadingLiveData.observe(this) {
            if (it){
                //progressDialog = ProgressDialog.show(this , "Loading" , "")
                showLoading()
            }else{
               // progressDialog.dismiss()
                dismissLoading()
            }

        }
        viewModel.massageLiveData.observe(this) { message->
            showAlertDialog(this , "Error" , message)
        }


    }

    override fun getLayoutFile(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this)[AddRoomViewModel::class.java]
    }

    override fun endActivity() {
        endActivity()
    }



//        override fun navigateToHome() {
//            val intent = Intent(this , HomeActivity::class.java)
//            startActivity(intent)
//        }


}