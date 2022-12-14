package com.tiendaunacho.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiendaunacho.model.libros
import com.tiendaunacho.repositorio.repo

class LibraryViewModel:ViewModel() {
      val repo=repo()
    fun libraryData():LiveData<MutableList<libros>>{
        val mutabledata=MutableLiveData<MutableList<libros>>()
        repo.getlibraryData().observeForever { result->
            mutabledata.value=result
        }
        return mutabledata
    }
}