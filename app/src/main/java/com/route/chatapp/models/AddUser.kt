package com.route.chatapp.models

data class AddUser(
    var Id : String ? = "" ,
    var firstName : String? = "" ,
    var lastName : String? = "",
    var email : String? = "",


){
    companion object{
        var COLLECTION_NAME = "usersData"
    }
}
