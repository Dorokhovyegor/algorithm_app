package com.dorokhov.androidreviewapp.ui.algorithmdetails

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

class AlgorithmDetailsVm @Inject constructor(
    val algorithmRepository: IAlgorithmRepository
) : BaseVm() {

    val algorithmState = BehaviorSubject.create<AlgorithmModel>()

    override fun createVmBinds() {
        algorithmRepository.getAlgorithm(1)
            .map { AlgorithmMapper.convertToPresentation(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.tag("Op").i("${it}")
                algorithmState.onNext(it)
            }, {
                Timber.e(it)
            })
            .addTo(binds)
    }

    fun getAlgorithm(): Observable<AlgorithmModel> {
        return algorithmState
    }

}