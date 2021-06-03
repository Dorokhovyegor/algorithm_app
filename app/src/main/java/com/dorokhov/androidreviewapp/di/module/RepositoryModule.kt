package com.dorokhov.androidreviewapp.di.module

import com.dorokhov.androidreviewapp.data.dao.AlgorithmDao
import com.dorokhov.androidreviewapp.domain.AlgorithmRepository
import com.dorokhov.androidreviewapp.domain.IAlgorithmRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAlgorithmRepository(
        algorithmDao: AlgorithmDao
    ): IAlgorithmRepository = AlgorithmRepository(algorithmDao)

}