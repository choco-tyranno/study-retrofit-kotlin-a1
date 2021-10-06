package com.choco_tyranno.masklocation.repository

import com.choco_tyranno.masklocation.model.StoreInfo
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface MaskService {
    @GET("storesByGeo/json/?m=5000")
    suspend fun fetchStoreInfo(
        @Query("lat") lat : Double,
        @Query("lng") lng : Double
    ): StoreInfo

    @GET(" ")
    suspend fun fetchStoreInfo(
    ): StoreInfo

    companion object{
        const val BASE_URL = "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/"
    }
}