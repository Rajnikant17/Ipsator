package com.example.ipsatorlocal.repository

import com.example.ipsatorlocal.util.BaseDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class PizzaDetailDataSource
@Inject
constructor(val pizzaApiServices: PizzaApiServices) :BaseDataSource() {
    suspend fun getPizza() = invoke { pizzaApiServices.getPizzaDetail() }
}