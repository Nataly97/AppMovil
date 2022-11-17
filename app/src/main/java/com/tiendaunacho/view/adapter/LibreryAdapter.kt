package com.tiendaunacho.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tiendaunacho.R
import com.tiendaunacho.model.libros
import android.content.Context

class LibreryAdapter(private val context: Context): RecyclerView.Adapter<LibreryAdapter.ViewHolder>() {
    private var libroslista= mutableListOf<libros>()

    fun setListData(data:MutableList<libros>){
        libroslista=data
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_librery, viewGroup, false)
        return ViewHolder(v)

    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
       fun binWew(libro:libros){
           itemView.findViewById<TextView>(R.id.title).text=libro.titulo
           itemView.findViewById<TextView>(R.id.descripcion).text=libro.descripcion
           itemView.findViewById<TextView>(R.id.precio).text=libro.precio
           Picasso.with(context).load(libro.image).into(itemView.findViewById<ImageView>(R.id.image))
       }
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int)  {
        val libro=libroslista[i]
        viewHolder.binWew(libro)
    }

    override fun getItemCount(): Int {
        return if(libroslista.size>0){
            libroslista.size
        }else{
            0
        }

    }

}