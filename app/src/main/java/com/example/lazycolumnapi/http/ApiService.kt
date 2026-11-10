package com.example.lazycolumnapi.http

import com.example.lazycolumnapi.model.Usuario
import retrofit2.http.GET

interface ApiService{
    @GET("users")
    suspend fun obtenerUsuarios(): List<Usuario>
}