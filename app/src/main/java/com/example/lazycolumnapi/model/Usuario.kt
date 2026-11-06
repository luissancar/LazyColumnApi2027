package com.example.lazycolumnapi.model

data class Usuario(
    val id: Int,
    val name: String,
    val email: String,
    val address: Direccion
)


data class Direccion(
    val city: String
)
