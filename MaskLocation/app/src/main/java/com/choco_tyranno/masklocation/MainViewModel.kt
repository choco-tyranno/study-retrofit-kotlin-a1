package com.choco_tyranno.masklocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choco_tyranno.masklocation.model.Store
import com.choco_tyranno.masklocation.model.StoreInfo
import com.choco_tyranno.masklocation.repository.MaskService
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: MaskService,
    private val fusedLocationClient : FusedLocationProviderClient
    ) : ViewModel(){

    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    init {
        fetchStoreInfo()
    }

    @SuppressLint("MissingPermission")
    fun fetchStoreInfo(){
        loadingLiveData.value = true
        fusedLocationClient.lastLocation.addOnSuccessListener { location->
            viewModelScope.launch {
                val storeInfo = service.fetchStoreInfo(location.latitude, location.longitude)
                itemLiveData.value = storeInfo.stores
                loadingLiveData.value = false
            }
        }.addOnFailureListener {exception->
            loadingLiveData.value = false
        }
    }
}