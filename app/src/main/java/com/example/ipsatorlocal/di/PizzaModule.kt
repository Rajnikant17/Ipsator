package com.example.ipsatorlocal.di

import com.example.ipsatorlocal.repository.PizzaDetailDataSource
import com.example.ipsatorlocal.repository.PizzaDetailRepository
import com.example.ipsatorlocal.repository.PizzaDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class PizzaModule {
    @Provides
    @ActivityRetainedScoped
    fun provideRepository(pizzaDetailDataSource: PizzaDetailDataSource): PizzaDetailRepository {
        return PizzaDetailRepositoryImpl(pizzaDetailDataSource)
    }
}