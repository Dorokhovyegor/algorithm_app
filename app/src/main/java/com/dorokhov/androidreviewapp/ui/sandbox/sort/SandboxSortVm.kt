package com.dorokhov.androidreviewapp.ui.sandbox.sort

import androidx.lifecycle.MutableLiveData
import com.dorokhov.androidreviewapp.baseui.BaseVm
import com.dorokhov.androidreviewapp.domain.provider.algorithm.AlgorithmType
import com.dorokhov.androidreviewapp.domain.provider.algorithm.ISortAlgorithmProvider
import com.dorokhov.androidreviewapp.domain.provider.prng.IPRNGProvider
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SandboxSortVm
@Inject constructor(
    private val algorithmProvider: ISortAlgorithmProvider,
    private val prngProvider: IPRNGProvider
) : BaseVm() {

    val numbers: MutableLiveData<Array<Int>> = MutableLiveData()
    val logs: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun createVmBinds() {}

    fun generateNumbers(size: Int) {
        Single.fromCallable {
            isLoading.postValue(true)
            prngProvider.provideArrayNumbers(if (size > 2000000) 2000000 else size)
        }.subscribeOn(Schedulers.computation())
            .subscribe({
                isLoading.postValue(false)
                numbers.postValue(it)
            }, {
                isLoading.postValue(false)
            }).addTo(binds)
    }

    fun startSort() {
        Single.fromCallable {
            isLoading.postValue(true)
            val algorithm = algorithmProvider.provideAlgorithm(AlgorithmType.InsertionSort)
            numbers.value ?: throw IllegalStateException("Array is null")
            val currentTime = System.currentTimeMillis()
            val sortedNumbers = algorithm.sort(numbers.value!!)
            val timeSort = System.currentTimeMillis() - currentTime
            prepareLogs(sortedNumbers, timeSort)
        }.subscribeOn(Schedulers.computation())
            .subscribe({
                isLoading.postValue(false)
            }, {
                isLoading.postValue(false)
                Timber.e(it)
            }).addTo(binds)
    }

    fun reset() {
        numbers.value = null
        logs.value = null
    }

    private fun prepareLogs(result: Array<Int>, timeResult: Long) {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Полученные числа: \n")
        if (result.size > 20) {
            stringBuilder.append("Чисел слишком много (${result.size})")
        } else {
            for (number in result) {
                stringBuilder.append("$number,")
            }
        }

        stringBuilder.append("\n")
        stringBuilder.append("Затраченное время: $timeResult мс.")
        logs.postValue(stringBuilder.toString())
    }
}