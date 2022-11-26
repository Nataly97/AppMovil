package com.tiendaunacho.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiendaunacho.model.compras
import com.tiendaunacho.repositorio.repo

class ComprasViewModel:ViewModel() {
    val repo=repo()
    fun fetchComprasData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        repo.getComprasData().observeForever {
            mutableData.value=it
        }
        return mutableData
    }
}