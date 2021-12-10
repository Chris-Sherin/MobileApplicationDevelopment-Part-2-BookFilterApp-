package com.example.bookfilterapp.data

import androidx.lifecycle.LiveData

class BookRepo(private val bookDao: BookDao) {

    val readAllBooks:LiveData<List<bookList>> = bookDao.getBooks()
    fun getBooksByauthor(author:String):LiveData<List<bookList>>
    {
        return bookDao.getAllBooks(author)
    }

    fun addBook(book: bookList)
    {
        bookDao.addBook(book)
    }

    fun addAuthor(author: Author)
    {
        bookDao.addAuthor(author)
    }
}