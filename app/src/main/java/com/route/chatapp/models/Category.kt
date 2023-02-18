package com.route.chatapp.models

import com.route.chatapp.R

data class Category(
    var id : String,
    var title: String,
    var imageId: Int
) {
    companion object{
        val Categories = mutableListOf<Category>(
            Category(id = "Sports" , title = "Sports" , imageId = R.drawable.sports),
            Category(id = "Music" , title = "Music" , imageId = R.drawable.music),
            Category(id = "Movies" , title = "Movies" , imageId = R.drawable.movies)
                   )
        fun getCategoryFromId(id: String): Category{
            when (id){
                "Sports" ->{
                    return Categories[0]
                }
                "Music" ->{return Categories[1]}
                "Movies"->{return Categories[2]}
            }
            return Categories[0]
        }
    }


}
