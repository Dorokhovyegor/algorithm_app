package com.dorokhov.androidreviewapp.domain.provider.algorithm

interface SortAlgorithm {
    fun sort(array: Array<Int>): Array<Int>
}