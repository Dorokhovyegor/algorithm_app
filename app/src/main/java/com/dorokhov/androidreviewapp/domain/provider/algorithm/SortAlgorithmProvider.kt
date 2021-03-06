package com.dorokhov.androidreviewapp.domain.provider.algorithm

import com.dorokhov.androidreviewapp.domain.sortalgorithms.BubbleSort
import com.dorokhov.androidreviewapp.domain.sortalgorithms.InsertionSort
import com.dorokhov.androidreviewapp.domain.sortalgorithms.MergeSort
import com.dorokhov.androidreviewapp.domain.sortalgorithms.QuickSort
import javax.inject.Inject

class SortAlgorithmProvider @Inject constructor() : ISortAlgorithmProvider {

    override fun provideAlgorithm(sortType: AlgorithmType): SortAlgorithm {
        return when (sortType) {
            is AlgorithmType.QuickSort -> QuickSort()
            is AlgorithmType.MergeSort -> MergeSort()
            is AlgorithmType.BubbleSort -> BubbleSort()
            is AlgorithmType.InsertionSort -> InsertionSort()
        }
    }
}