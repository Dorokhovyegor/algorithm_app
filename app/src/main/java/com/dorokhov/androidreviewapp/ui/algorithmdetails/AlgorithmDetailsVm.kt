package com.dorokhov.androidreviewapp.ui.algorithmdetails

import androidx.lifecycle.MutableLiveData
import com.dorokhov.androidreviewapp.baseui.BaseVm
import com.dorokhov.androidreviewapp.domain.IAlgorithmRepository
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel
import com.dorokhov.androidreviewapp.ui.mapper.AlgorithmMapper
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class AlgorithmDetailsVm
@Inject constructor(
    private val algorithmRepository: IAlgorithmRepository
) : BaseVm() {

    val algorithm: MutableLiveData<AlgorithmModel> = MutableLiveData()
    val fullDescriptionVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val implementationVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun createVmBinds() {
        algorithmRepository.getAlgorithm(1)
            .map { AlgorithmMapper.convertToPresentation(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                algorithm.postValue(it)
            }, {
                Timber.e(it)
            })
            .addTo(binds)
    }

    fun changeVisibilityDescription() {
        fullDescriptionVisible.value = fullDescriptionVisible.value?.not()
    }

    fun changeVisibilityImplementation() {
        implementationVisible.value = implementationVisible.value?.not()
    }
}