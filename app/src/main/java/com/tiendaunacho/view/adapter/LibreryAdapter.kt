package com.tiendaunacho.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tiendaunacho.R

class LibreryAdapter:RecyclerView.Adapter <LibreryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_view_librery, viewGroup, false)
        return ViewHolder(v)

    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDescripcion: TextView
        var itemPrecio: TextView

        init {
            itemImage=ItemView.findViewById(R.id.image)
            itemTitle=ItemView.findViewById(R.id.title)
            itemDescripcion=ItemView.findViewById(R.id.descripcion)
            itemPrecio=ItemView.findViewById(R.id.precio)

        }
    }
        val titles= arrayOf("libro1","libro2","libro3","libro4","libro5")
        val descripcion= arrayOf("lomejor del libro1","lomejor del libro2","lomejor del libro3","lomejor del libro4","lomejor del libro5")
        val precio= arrayOf("$10.000","$20.000","$30.000","$40.000","$50.000")
        val image= arrayOf(R.drawable.cronicadeunamuerteaninciada,R.drawable.elamorenlostiemposdelcolera,R.drawable.elcoronelnotienequienleescriba,R.drawable.cronicadeunamuerteaninciada,R.drawable.lahojarasca,R.drawable.lamalahora)

    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int)  {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemDescripcion.text=descripcion[i]
        viewHolder.itemPrecio.text=precio[i]
        viewHolder.itemImage.setImageResource(image[i])
    }

    override fun getItemCount(): Int {
        return titles.size

    }

}
