package com.example.giftshopsunmulapp.model
import kotlinx.serialization.Serializable

@Serializable
data class paymentMethods(
    val id: String,
    val title: String
)
