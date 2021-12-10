package com.example.bookfilterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Books")
data class bookList(
    @PrimaryKey(autoGenerate = true)
    val book_id:Int,
    val title:String,
    val author:String,
    val language:String,
    val year:Int,
    val pages:Int
)



