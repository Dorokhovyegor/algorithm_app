package com.dorokhov.androidreviewapp.default

import com.dorokhov.androidreviewapp.data.entity.AlgorithmEntity

object AndroidDefaultContent {
    val quickSort = AlgorithmEntity(
        id = 1,
        title = "Быстрая сортировка",
        shortDescription = "Один из самых быстрых известных универсальных алгоритмов сортировки массивов: в среднем O ( n log \u2061 n ) {\\displaystyle O(n\\log n)} O(n\\log n) обменов при упорядочении n {\\displaystyle n} n элементов; из-за наличия ряда недостатков на практике обычно используется с некоторыми доработками. ",
        fullDescription = "Его разработал в 1960 году английский ученый Чарльз Хоар, занимавшийся тогда в МГУ машинным переводом. Алгоритм, по принципу функционирования, входит в класс обменных сортировок (сортировка перемешиванием, пузырьковая сортировка и др.), выделяясь при этом высокой скоростью работы.\n" +
                "\n" +
                "Отличительной особенностью быстрой сортировки является операция разбиения массива на две части относительно опорного элемента. Например, если последовательность требуется упорядочить по возрастанию, то в левую часть будут помещены все элементы, значения которых меньше значения опорного элемента, а в правую элементы, чьи значения больше или равны опорному.\n" +
                "\n" +
                "Вне зависимости от того, какой элемент выбран в качестве опорного, массив будет отсортирован, но все же наиболее удачным считается ситуация, когда по обеим сторонам от опорного элемента оказывается примерно равное количество элементов. Если длина какой-то из получившихся в результате разбиения частей превышает один элемент, то для нее нужно рекурсивно выполнить упорядочивание, т. е. повторно запустить алгоритм на каждом из отрезков.",
        sourceCode = "fun quickSort(array: Array<Int>, start: Int, end: Int) {\n" +
                "    if (start >= end) return\n" +
                "    var startEl = start\n" +
                "    var endEl = end\n" +
                "    var pivot = startEl - (startEl - endEl) / 2\n" +
                "    while (startEl < endEl) {\n" +
                "        while (startEl < pivot && array[startEl] <= array[pivot]) {\n" +
                "            startEl++\n" +
                "        }\n" +
                "        while (endEl > pivot && array[pivot] <= array[endEl]) {\n" +
                "            endEl--\n" +
                "        }\n" +
                "\n" +
                "        if (startEl < endEl) {\n" +
                "            val temp = array[startEl]\n" +
                "            array[startEl] = array[endEl]\n" +
                "            array[endEl] = temp\n" +
                "            when {\n" +
                "                startEl == pivot -> pivot = endEl\n" +
                "                endEl == pivot -> pivot = startEl\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    customQuickSort1(array, start, pivot)\n" +
                "    customQuickSort1(array, pivot + 1, end)\n" +
                "}",
        algorithmType = 0,
        bigONotationType = 0,
        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Sorting_quicksort_anim.gif/274px-Sorting_quicksort_anim.gif"
    )
}