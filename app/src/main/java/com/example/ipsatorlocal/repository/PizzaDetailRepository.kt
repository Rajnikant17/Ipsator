package com.example.ipsatorlocal.repository

import com.example.ipsatorlocal.util.DataState
import com.example.ipsatorlocal.util.PizzaDetail

interface PizzaDetailRepository {
   suspend fun getPizzaDetail():DataState<PizzaDetail?>
}