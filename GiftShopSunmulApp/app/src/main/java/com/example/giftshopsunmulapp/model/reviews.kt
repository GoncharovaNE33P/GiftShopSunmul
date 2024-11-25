package com.example.giftshopsunmulapp.model

import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class reviews(
    val id: String,
    val users_id: String,
    val products_id: String,
    val estimation: Int,
    val comment: String,
    val date_rev: String,

    var users: List<user>? = null,
    var products: products? = null,
)
