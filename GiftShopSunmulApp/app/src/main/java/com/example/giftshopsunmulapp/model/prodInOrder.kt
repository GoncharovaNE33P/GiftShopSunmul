package com.example.giftshopsunmulapp.model
import kotlinx.serialization.Serializable

@Serializable
data class prodInOrder(
    val id: String,
    val products_id: String,
    val orders_id: String,
    val count: Int,

    var products: products? = null,
    var orders: orders? = null
)
