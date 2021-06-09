package com.dorokhov.androidreviewapp.di.module

import com.dorokhov.androidreviewapp.domain.provider.algorithm.ISortAlgorithmProvider
import com.dorokhov.androidreviewapp.domain.provider.algorithm.SortAlgorithmProvider
import com.dorokhov.androidreviewapp.domain.provider.prng.IPRNGProvider
import com.dorokhov.androidreviewapp.domain.provider.prng.PRNGProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AlgorithmModule {

    @Provides
    @Singleton
    fun provideAlgorithmProvider(): ISortAlgorithmProvider = SortAlgorithmProvider()

    @Provides
    @Singleton
    fun providePrng(): IPRNGProvider = PRNGProvider()

}