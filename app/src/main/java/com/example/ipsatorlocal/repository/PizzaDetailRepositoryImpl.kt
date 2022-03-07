package com.example.ipsatorlocal.repository

import com.example.ipsatorlocal.util.DataState
import com.example.ipsatorlocal.util.PizzaDetail

class PizzaDetailRepositoryImpl(val pizzaDetailDataSource: PizzaDetailDataSource) :PizzaDetailRepository{
   override suspend fun getPizzaDetail(): DataState<PizzaDetail?> {
        return pizzaDetailDataSource.getPizza()
    }
}