package com.example.giftshopsunmulapp.model
import kotlinx.serialization.Serializable

@Serializable
data class history(
    val id: String,
    val users_id: String,
    val products_id: String,
    val orders_id: String,

    var country: countryProd? = null,
    var categories: categories? = null,
    var prodStatus: productsStatus? = null,
)
