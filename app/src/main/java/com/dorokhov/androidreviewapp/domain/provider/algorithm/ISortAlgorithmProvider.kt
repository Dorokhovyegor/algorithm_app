package com.dorokhov.androidreviewapp.domain.provider.algorithm

interface ISortAlgorithmProvider {
    fun provideAlgorithm(sortType: AlgorithmType): SortAlgorithm
}