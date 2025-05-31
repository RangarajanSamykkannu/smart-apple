package com.example.composetabviewwithlocalcrud.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResApiService {
    @GET("users") // Endpoint path
    suspend fun getUsers(@Query("page") page: Int): UserListResponse
}
