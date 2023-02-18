package com.route.chatapp.firebase

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.route.chatapp.models.AddUser
import com.route.chatapp.models.Message
import com.route.chatapp.models.Room
fun addToFireStore(user: AddUser , onSuccessListener:OnSuccessListener<in Void>,
                                    onFailureListener: OnFailureListener){
    var fireStore = Firebase.firestore
   var documentRef =  fireStore.collection(AddUser.COLLECTION_NAME).document(user.Id?:"")
    documentRef.set(user).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener)
}
fun getUserFromFireStore(userId : String , success: OnSuccessListener<DocumentSnapshot>, failure: OnFailureListener ) {
    var fireStore = Firebase.firestore
    var documentSnap = fireStore.collection(AddUser.COLLECTION_NAME)
        .document(userId).get().addOnSuccessListener(success)
        .addOnFailureListener(failure)
}

fun addRoomToFireStore(room: Room , onSuccessListener:OnSuccessListener<in Void>,
                         onFailureListener: OnFailureListener){
      var fireStore = Firebase.firestore
     var docRef =  fireStore.collection(Room.COLLECTION_NAME).document()
      room.id = docRef.id
      docRef.set(room).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener)
  }
fun getAllRooms(onSuccessListener: OnSuccessListener<in QuerySnapshot> , onFailureListener: OnFailureListener){
    var fireStore = Firebase.firestore
    fireStore.collection(Room.COLLECTION_NAME).get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}
fun addMessageToFireStore(roomId : String , message: Message , onSuccessListener:OnSuccessListener<in Void>,
                onFailureListener: OnFailureListener){
    var fireStore = Firebase.firestore
    val docRef = fireStore.collection(Room.COLLECTION_NAME).document(roomId)
        .collection(Message.COLLECTION_NAME).document()
    message.id = docRef.id
    docRef.set(message).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener)

}
