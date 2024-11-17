package com.example.giftshopsunmulapp.model

import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class orders(
    val id: String,
    val users_id: String,
    val article: String,
    val date_order: String,
    val date_delivery: String,
    val order_status: String,
    val sum: Int,
    val payment_methods: String,
    val delivery_methods: String,
    val delivery_address: String,
)
