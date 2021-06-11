package com.dorokhov.androidreviewapp.domain.provider.algorithm

sealed class AlgorithmType {
    object QuickSort: AlgorithmType()
    object MergeSort: AlgorithmType()
    object BubbleSort: AlgorithmType()
    object InsertionSort: AlgorithmType()
}