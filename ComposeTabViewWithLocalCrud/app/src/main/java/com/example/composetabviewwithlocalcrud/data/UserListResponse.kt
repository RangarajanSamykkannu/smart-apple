package com.example.composetabviewwithlocalcrud.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListResponse(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val data: List<User>
    // val support: Support // Assuming 'support' object from reqres.in is not needed for now
)

// @Serializable
// data class Support(
//     val url: String,
//     val text: String
// )
// Commenting out Support class as it's likely not used immediately.
