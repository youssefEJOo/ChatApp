package com.route.chatapp.ui.home_activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.route.chatapp.R
import com.route.chatapp.BaseActivity
import com.route.chatapp.databinding.ActivityAddRoomBinding
import com.route.chatapp.databinding.ActivityHomeBinding
import com.route.chatapp.firebase.getAllRooms
import com.route.chatapp.models.Room
import com.route.chatapp.ui.add_room.AddRoomActivity
import com.route.chatapp.ui.chat_activity.ChatActivity

class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding >() , Navigator {
    lateinit var adapter: RoomAdapter
    var roomList  = mutableListOf<Room>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        viewDataBinding.vm = viewModel
        initViews()
    }


    fun initViews(){
        subscribeToLiveData()
        adapter = RoomAdapter(roomList)
        adapter.onRoomClick = object  : RoomAdapter.OnRoomClick{
            override fun onRoomClicked(position: Int, room: Room) {
                val intent = Intent(baseContext , ChatActivity::class.java)
                intent.putExtra("roomId" , room.id)
                startActivity(intent)
            }
        }
        viewDataBinding.roomsRecyclerView.adapter = adapter
        viewDataBinding.roomsRecyclerView.layoutManager = GridLayoutManager(this , 2 , RecyclerView.VERTICAL ,false)
    }

 fun subscribeToLiveData(){
     viewModel.roomList.observe(this ,){room->
         Log.e("subscribeToLiveData" , "${room.title}")
         roomList.add(room)
         adapter.notifyDataSetChanged()
        // Log.e("subscribeToLiveData" , "${}")
     }
     viewModel.progressLoadingLiveData.observe(this){
         if (it){
             showLoading()
         }else{
             dismissLoading()
         }
     }
     viewModel.massageLiveData.observe(this){
         showAlertDialog(this , "Error" , it)
     }
 }

    override fun onResume() {
        super.onResume()
        viewModel.getRooms()

    }

    override fun getLayoutFile(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun navigateToAddRoom() {
        val intent = Intent(this , AddRoomActivity::class.java )
        startActivity(intent)
    }


}