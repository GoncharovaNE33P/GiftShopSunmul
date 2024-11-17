package com.example.giftshopsunmulapp.model
import kotlinx.serialization.Serializable

@Serializable
data class shopCart(
    val id: String,
    val users_id: String,
    val products_id: String,
    val count: Int
)
