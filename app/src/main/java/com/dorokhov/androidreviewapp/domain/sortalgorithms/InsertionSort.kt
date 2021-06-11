package com.dorokhov.androidreviewapp.domain.sortalgorithms

import com.dorokhov.androidreviewapp.domain.provider.algorithm.SortAlgorithm

class InsertionSort : SortAlgorithm {
    override fun sort(array: Array<Int>): Array<Int> {
        insertionSort(array)
        return array
    }

   private fun insertionSort(array: Array<Int>) {
        val n = array.size
        for (j in 1 until n) {
            val key = array[j]
            var i = j - 1
            while (i > -1 && array[i] > key) {
                array[i + 1] = array[i]
                i--
            }
            array[i + 1] = key
        }
    }
}