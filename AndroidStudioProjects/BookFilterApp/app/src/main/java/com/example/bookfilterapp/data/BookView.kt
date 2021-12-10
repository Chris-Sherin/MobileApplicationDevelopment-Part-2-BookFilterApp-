package com.example.bookfilterapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookView(application: Application): AndroidViewModel(application) {

    private val repository:BookRepo
    val readAllBooks:LiveData<List<bookList>>
    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository= BookRepo(bookDao)
        readAllBooks=bookDao.getBooks()
    }
    fun getBooksByAuthor(author:String):LiveData<List<bookList>>
    {
        return repository.getBooksByauthor(author)
    }

    fun addBook(book: bookList)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }
    fun addAuthor(author: Author)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAuthor(author)
        }
    }
}