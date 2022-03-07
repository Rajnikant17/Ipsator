package com.example.ipsatorlocal.usecases

import com.example.ipsatorlocal.repository.PizzaDetailRepository
import com.example.ipsatorlocal.util.DataState
import com.example.ipsatorlocal.util.PizzaDetail
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class PizzaDetailUseCase
@Inject
constructor(val pizzaDetailRepository: PizzaDetailRepository)
{
    suspend fun getPizzaDetail():Flow<DataState<PizzaDetail?>> = flow {
        emit(DataState.Loading)
        val response=pizzaDetailRepository.getPizzaDetail()
        when(response.statusCode){
            200->emit(DataState.Success(response.data,response.statusCode))
            else ->emit(DataState.Error(response.statusCode, null,response.errorMsg))
        }
    }
}