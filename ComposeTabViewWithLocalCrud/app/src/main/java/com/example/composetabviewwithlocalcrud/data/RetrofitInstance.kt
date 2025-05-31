package com.example.composetabviewwithlocalcrud.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "https://reqres.in/api/"

    // Configure Json to be lenient for unknown keys if necessary
    private val json = Json { ignoreUnknownKeys = true }

    val api: ReqResApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ReqResApiService::class.java)
    }
}
