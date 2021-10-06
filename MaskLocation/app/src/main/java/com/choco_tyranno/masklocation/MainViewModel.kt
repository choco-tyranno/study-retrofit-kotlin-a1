package com.choco_tyranno.masklocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choco_tyranno.masklocation.model.Store
import com.choco_tyranno.masklocation.model.StoreInfo
import com.choco_tyranno.masklocation.repository.MaskService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: MaskService
    ) : ViewModel(){

    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    init {
        fetchStoreInfo()
    }

    fun fetchStoreInfo(){
        loadingLiveData.value = true
        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo()
            itemLiveData.value = storeInfo.stores
            loadingLiveData.value = false
        }
    }
}