package com.example.giftshopsunmulapp.model
import kotlinx.serialization.Serializable

@Serializable
data class products(
    val id: String,
    val article: Int,
    val title: String,
    val grams: Int,
    val sing_18: Boolean,
    val description: String,
    val price: Int,
    val image: String,
    val countRev: Int,
    val rating: Float,
    val country_id: String,
    val categories_id: String,
    val prodStatus_id: String,

    var country: countryProd? = null,
    var categories: categories? = null,
    var prodStatus: productsStatus? = null,
)
