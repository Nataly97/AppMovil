package com.tiendaunacho.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tiendaunacho.R
import com.tiendaunacho.model.compras

class ComprasAdapter(private val context: Context, var clickListener: OnCompraItemClickListener): RecyclerView.Adapter<ComprasAdapter.ViewHolder>() {
    private var libroslista= mutableListOf<compras>()

    fun setListData(data:MutableList<compras>){
        libroslista=data
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_compras,
            viewGroup, false)
        return ViewHolder(v)

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int)  {
        val libro=libroslista[i]
        viewHolder.binWew(libro, clickListener)
    }

    override fun getItemCount(): Int {
        return if(libroslista.size>0){
            libroslista.size
        }else{
            0
        }

    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        fun binWew(libro: compras, action: OnCompraItemClickListener){
            itemView.findViewById<TextView>(R.id.title).text=libro.titulo
            itemView.findViewById<TextView>(R.id.descripcion).text=libro.descripcion
            itemView.findViewById<TextView>(R.id.precio).text=libro.precio
            Picasso.with(context).load(libro.image).into(itemView.findViewById<ImageView>(R.id.image))
            val btneliminar=itemView.findViewById<ImageButton>(R.id.eliminar)
            btneliminar.setOnClickListener {
                action.onItemclick(libro, adapterPosition)
            }
        }
    }

}
interface OnCompraItemClickListener{
    fun onItemclick(libro: compras, position:Int)
}