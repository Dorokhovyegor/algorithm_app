package com.dorokhov.androidreviewapp.ui.sandbox.sort

import androidx.lifecycle.MutableLiveData
import com.dorokhov.androidreviewapp.baseui.BaseVm
import com.dorokhov.androidreviewapp.domain.provider.algorithm.AlgorithmType
import com.dorokhov.androidreviewapp.domain.provider.algorithm.ISortAlgorithmProvider
import com.dorokhov.androidreviewapp.domain.provider.prng.IPRNGProvider
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SandboxSortVm
@Inject constructor(
    private val algorithmProvider: ISortAlgorithmProvider,
    private val prngProvider: IPRNGProvider
) : BaseVm() {

    val numbers: MutableLiveData<Array<Int>> = MutableLiveData()
    val logs: MutableLiveData<String> = MutableLiveData()

    override fun createVmBinds() {}

    fun generateNumbers(size: Int) {
        numbers.value = prngProvider.provideArrayNumbers(size)
    }

    fun generateArrayFromEt(value: String) {
        val array = value.split(" ").map {
            it.toIntOrNull() ?: 0
        }
        numbers.value = array.toTypedArray()
    }

    fun startSort() {
        Single.create<Array<Int>> {
            val algorithm = algorithmProvider.provideAlgorithm(AlgorithmType.QuickSort)
            numbers.value ?: throw IllegalStateException("Array is null")
            val currentTime = System.currentTimeMillis()
            val sortedNumbers = algorithm.sort(numbers.value!!)
            val timeSort = System.currentTimeMillis() - currentTime
            prepareLogs(sortedNumbers, timeSort)
        }.subscribeOn(Schedulers.computation())
            .subscribe().addTo(binds)
    }

    private fun prepareLogs(result: Array<Int>, timeResult: Long) {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Полученные числа: \n")
        if (result.size > 20) {
            stringBuilder.append("Чисел слишком много")
        } else {
            for (number in result) {
                stringBuilder.append("$number|")
            }
        }

        stringBuilder.append("\n")
        stringBuilder.append("Затраченное время: $timeResult мс.")
        logs.postValue(stringBuilder.toString())
    }
}