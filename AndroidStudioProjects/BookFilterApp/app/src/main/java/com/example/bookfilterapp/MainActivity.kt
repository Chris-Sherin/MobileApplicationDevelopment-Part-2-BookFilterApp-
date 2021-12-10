package com.example.bookfilterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookfilterapp.data.Author
import com.example.bookfilterapp.data.bookList
import com.example.bookfilterapp.data.MyApplication
import com.example.bookfilterapp.data.BookView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var mBookViewModel:BookView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBookViewModel = ViewModelProvider(this).get(BookView::class.java)
        findViewById<TextView>(R.id.downloadTextView).setOnClickListener()
        {
            insertDataToDatabase()
        }
        val adapter = BooksAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)

        val author=findViewById<TextInputLayout>(R.id.AuthorInput)?.editText?.text

        findViewById<Button>(R.id.button).setOnClickListener()
        {
            findViewById<TextView>(R.id.title_view).text="Id"
            findViewById<TextView>(R.id.titleNameView).text="Book Title"
            findViewById<TextView>(R.id.yearView).text="Year"
            mBookViewModel.getBooksByAuthor(author.toString()).observe(this,{book->
                adapter.setData(book)
            })
        }
    }

    private fun insertDataToDatabase() {
        val bookApplication=application as MyApplication
        val bookService=bookApplication.books

        CoroutineScope(Dispatchers.IO).launch {
            val decodedBook = bookService.getFilter()
            withContext(Dispatchers.Main)
            {
                for(myData in decodedBook) {
                    val book =bookList(0,myData.title,myData.author,myData.language,myData.year.toInt(),myData.pages.toInt())
                    val author=Author(0,myData.author,myData.country)
                    mBookViewModel.addAuthor(author)
                    mBookViewModel.addBook(book)
                }
            }
        }
    }
}