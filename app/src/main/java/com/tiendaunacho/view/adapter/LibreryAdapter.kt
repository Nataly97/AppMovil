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
import android.widget.ImageButton

class LibreryAdapter(private val context: Context,var clickListener: OnBookItemClickListener): RecyclerView.Adapter<LibreryAdapter.ViewHolder>() {
    private var libroslista= mutableListOf<libros>()

    fun setListData(data:MutableList<libros>){
        libroslista=data
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_librery, viewGroup, false)
        return ViewHolder(v)

    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
       fun binWew(libro:libros, action:OnBookItemClickListener){
           itemView.findViewById<TextView>(R.id.title).text=libro.titulo
           itemView.findViewById<TextView>(R.id.descripcion).text=libro.descripcion
           itemView.findViewById<TextView>(R.id.precio).text=libro.precio
           Picasso.with(context).load(libro.image).into(itemView.findViewById<ImageView>(R.id.image))
           val btncarrito=itemView.findViewById<ImageButton>(R.id.carrito)
           btncarrito.setOnClickListener {
               action.onItemclick(libro,adapterPosition)
           }
       }
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int)  {
        val libro=libroslista[i]
        viewHolder.binWew(libro,clickListener)
    }

    override fun getItemCount(): Int {
        return if(libroslista.size>0){
            libroslista.size
        }else{
            0
        }

    }
 
}
interface OnBookItemClickListener{
    fun  onItemclick(libro: libros,position:Int)
}