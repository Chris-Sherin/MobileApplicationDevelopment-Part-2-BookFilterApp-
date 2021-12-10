package com.example.bookfilterapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAuthor(author: Author)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBook(book: bookList)

    @Query("SELECT * FROM Books where author=:author limit 3")
    fun getAllBooks(author:String):LiveData<List<bookList>>

    @Query("Select * from Books")
    fun getBooks():LiveData<List<bookList>>
}