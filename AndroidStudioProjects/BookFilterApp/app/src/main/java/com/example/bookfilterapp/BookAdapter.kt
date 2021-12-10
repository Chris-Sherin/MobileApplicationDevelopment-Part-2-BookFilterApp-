package com.example.bookfilterapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookfilterapp.data.bookList

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {
    var bookList= emptyList<bookList>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=bookList[position]
        holder.itemView.findViewById<TextView>(R.id.idView).text=currentItem.book_id.toString()
        holder.itemView.findViewById<TextView>(R.id.bookText).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.yearText).text=currentItem.year.toString()
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
    fun setData(books:List<bookList>)
    {
        this.bookList=books
        notifyDataSetChanged()
    }
}