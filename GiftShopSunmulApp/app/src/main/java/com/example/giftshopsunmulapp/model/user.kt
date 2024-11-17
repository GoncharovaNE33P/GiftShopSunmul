package com.example.giftshopsunmulapp.model
import kotlinx.serialization.Serializable

@Serializable
data class user(
    val id: String,
    val name: String,
    val phone: String,
    val birthday: String,
    val image: String?
)
