package com.choco_tyranno.masklocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choco_tyranno.masklocation.model.Store
import com.choco_tyranno.masklocation.model.StoreInfo
import com.choco_tyranno.masklocation.repository.MaskService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel(){
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private val service : MaskService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        service = retrofit.create(MaskService::class.java)
        fetchStoreInfo()
    }

    fun fetchStoreInfo(){
        loadingLiveData.value = true
        viewModelScope.launch {
//            val storeInfo = service.fetchStoreInfo(37.0, 127.0)
            val storeInfo = service.fetchStoreInfo()
            itemLiveData.value = storeInfo.stores
            loadingLiveData.value = false
        }
    }
}