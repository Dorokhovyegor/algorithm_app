package com.dorokhov.androidreviewapp.domain.sortalgorithms

import com.dorokhov.androidreviewapp.domain.provider.algorithm.SortAlgorithm

class MergeSort : SortAlgorithm {
    override fun sort(array: Array<Int>): Array<Int> {
        mergeSort(array, 0, array.size - 1)
        return array
    }

    private fun mergeSort(arr: Array<Int>, l: Int, r: Int) {
        if (l < r) //(l<r) condition will hold good until getting singleton arrays
        {
            val mid: Int = (l + r) / 2
            mergeSort(arr, l, mid) //calling merge sort on left sub array
            mergeSort(arr, mid + 1, r) // calling merge sort on right sub array
            merge(arr, l, mid, r) // merge operation
        }
    }

    private fun merge(arr: Array<Int>, l: Int, mid: Int, r: Int) {
        val n1 = mid - l + 1 //getting size of left sub array
        val n2 = r - mid //getting size of right sub array
        val left = IntArray(n1)
        val right = IntArray(n2)
        var i: Int = 0
        while (i < n1) {
            left[i] = arr[l + i]
            i++
        }
        i = 0
        while (i < n2) {
            right[i] = arr[mid + 1 + i]
            i++
        }
        var li: Int = 0 //left index
        var ri: Int = 0 //right index
        var ai: Int = l //array index
        while (li < n1 && ri < n2) {
            if (left[li] <= right[ri]) // minimum element will be placed in sorted sub array
            {
                arr[ai] = left[li]
                ai++
                li++
            } else {
                arr[ai] = right[ri]
                ai++
                ri++
            }
        }
        while (li < n1) // copy remaining elements of left sub array into the merged array
        {
            arr[ai] = left[li]
            ai++
            li++
        }
        while (ri < n2) //copy remaining elements of right sub array into the merged array
        {
            arr[ai] = right[ri]
            ai++
            ri++
        }
    }
}