package com.mykoatlincoadingsample.connectivity

import com.mykoatlincoadingsample.BuildConfig
import com.mykoatlincoadingsample.model.Result
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiInterface {

    @GET
    suspend fun getEmployeeList(@Url urlEndPoint: String): Response<Result>

    companion object {
        operator fun invoke(): ApiInterface {
            return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
                .create(ApiInterface::class.java)
        }
    }
}