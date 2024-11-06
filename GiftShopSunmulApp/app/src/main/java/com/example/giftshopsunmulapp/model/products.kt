package com.example.giftshopsunmulapp.model

data class products(
    val id: String,
    val article: String,
    val title: String,
    val grams: Int,
    val sing_18: Boolean,
    val description: String,
    val price: Int,
    val image: String,
    val rating: Float,
    val country_id: String,
    val categories_id: String,
    val prodStatus_id: String
)
