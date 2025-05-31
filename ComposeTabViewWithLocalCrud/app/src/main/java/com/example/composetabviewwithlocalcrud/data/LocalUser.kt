package com.example.composetabviewwithlocalcrud.data

import kotlinx.serialization.Serializable

@Serializable // Useful if you plan to save this to disk with Kotlinx Serialization
data class LocalUser(
    val id: Int, // Can be the original API user ID, or a new one if created from scratch
    var email: String,
    var firstName: String,
    var lastName: String,
    var avatar: String, // URL
    var address: String = "",
    var country: String = "",
    var phone: String = ""
)
