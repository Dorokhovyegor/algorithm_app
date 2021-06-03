package com.dorokhov.androidreviewapp.ui.algorithms

import com.dorokhov.androidreviewapp.baseui.BaseVm
import com.dorokhov.androidreviewapp.navigation.screens.AlgorithmDetailsScreen
import com.dorokhov.androidreviewapp.navigation.subnavigation.LocalCiceroneHolder
import com.dorokhov.androidreviewapp.ui.MainActivity.Companion.ALGORITHM_TAB
import javax.inject.Inject

class AlgorithmVm @Inject constructor(
    private val localCiceroneHolder: LocalCiceroneHolder
) : BaseVm() {

    override fun createVmBinds() {

    }

    fun navigateTo() {
        localCiceroneHolder.getCicerone(ALGORITHM_TAB).router.navigateTo(AlgorithmDetailsScreen)
    }

}