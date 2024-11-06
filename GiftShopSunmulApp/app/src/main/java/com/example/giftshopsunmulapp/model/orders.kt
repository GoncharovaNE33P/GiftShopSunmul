package com.example.giftshopsunmulapp.model

import java.util.Date

data class orders(
    val id: String,
    val users_id: String,
    val article: String,
    val date_order: Date,
    val date_delivery: Date,
    val order_status: String,
    val sum: Int,
    val payment_methods: String,
    val delivery_methods: String,
    val delivery_address: String,
)
