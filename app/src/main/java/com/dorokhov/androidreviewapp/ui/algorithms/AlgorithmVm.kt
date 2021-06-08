package com.dorokhov.androidreviewapp.ui.algorithms

import com.dorokhov.androidreviewapp.baseui.BaseVm
import com.dorokhov.androidreviewapp.default.AndroidDefaultContent
import com.dorokhov.androidreviewapp.domain.IAlgorithmRepository
import com.dorokhov.androidreviewapp.navigation.screens.AlgorithmDetailsScreen
import com.dorokhov.androidreviewapp.navigation.subnavigation.LocalCiceroneHolder
import com.dorokhov.androidreviewapp.ui.MainActivity.Companion.ALGORITHM_TAB
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlgorithmVm @Inject constructor(
    private val localCiceroneHolder: LocalCiceroneHolder,
    private val algorithmRepository: IAlgorithmRepository
) : BaseVm() {

    override fun createVmBinds() {
        algorithmRepository.insetAlgorithm(AndroidDefaultContent.quickSort)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun navigateTo() {
        localCiceroneHolder.getCicerone(ALGORITHM_TAB).router.navigateTo(AlgorithmDetailsScreen)
    }

}