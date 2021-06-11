package com.dorokhov.androidreviewapp.domain.sortalgorithms

import com.dorokhov.androidreviewapp.domain.provider.algorithm.SortAlgorithm

class BubbleSort : SortAlgorithm {
    override fun sort(array: Array<Int>): Array<Int> {
        bubbleSort(array)
        return array
    }

    private fun bubbleSort(array: Array<Int>) {
        val n: Int = array.size
        for (i in 0 until n - 1) for (j in 0 until n - i - 1) if (array[j] > array[j + 1]) {
            val temp: Int = array[j]
            array[j] = array[j + 1]
            array[j + 1] = temp
        }
    }
}