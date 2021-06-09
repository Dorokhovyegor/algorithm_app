package com.dorokhov.androidreviewapp.domain.sortalgorithms

import com.dorokhov.androidreviewapp.domain.provider.algorithm.SortAlgorithm

class QuickSort : SortAlgorithm {
    override fun sort(array: Array<Int>): Array<Int> {
        quickSort(array, 0, array.size - 1)
        return array
    }

    private fun quickSort(array: Array<Int>, start: Int, end: Int) {
        if (start >= end) return
        var numberInWrongPlaceStart = start
        var numberInWrongPlaceEnd = end
        var pivot = numberInWrongPlaceStart - (numberInWrongPlaceStart - numberInWrongPlaceEnd) / 2
        while (numberInWrongPlaceStart < numberInWrongPlaceEnd) {
            while (numberInWrongPlaceStart < pivot && array[numberInWrongPlaceStart] <= array[pivot]) {
                numberInWrongPlaceStart++
            }
            while (numberInWrongPlaceEnd > pivot && array[pivot] <= array[numberInWrongPlaceEnd]) {
                numberInWrongPlaceEnd--
            }

            if (numberInWrongPlaceStart < numberInWrongPlaceEnd) {
                val temp = array[numberInWrongPlaceStart]
                array[numberInWrongPlaceStart] = array[numberInWrongPlaceEnd]
                array[numberInWrongPlaceEnd] = temp
                when {
                    numberInWrongPlaceStart == pivot -> pivot = numberInWrongPlaceEnd
                    numberInWrongPlaceEnd == pivot -> pivot = numberInWrongPlaceStart
                }
            }
        }
        quickSort(array, start, pivot)
        quickSort(array, pivot + 1, end)
    }
}