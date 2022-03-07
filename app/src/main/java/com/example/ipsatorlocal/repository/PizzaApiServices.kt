package com.example.ipsatorlocal.repository

import com.example.ipsatorlocal.util.PizzaDetail
import retrofit2.Response
import retrofit2.http.GET

interface PizzaApiServices {
    @GET("/pizzas")
    suspend fun getPizzaDetail(): Response<PizzaDetail>
}