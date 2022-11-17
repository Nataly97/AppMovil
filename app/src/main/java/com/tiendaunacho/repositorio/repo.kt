package com.tiendaunacho.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tiendaunacho.model.libros

class repo {
    fun getlibraryData():LiveData<MutableList<libros>>{
        val mutabledata=MutableLiveData<MutableList<libros>>()

        FirebaseFirestore.getInstance().collection("libros").get()
            .addOnSuccessListener { result->
            val listData= mutableListOf<libros>()
            for(document in result){
                val titulo=document.getString("titulo")
                val descripcion=document.getString("descripcion")
                val precio=document.getString("precio")
                val image=document.getString("image")
                val libro=libros(titulo!!,descripcion!!,precio!!,image!!)
                listData.add(libro)
            }
            mutabledata.value=listData
        }
        return mutabledata
    }
}