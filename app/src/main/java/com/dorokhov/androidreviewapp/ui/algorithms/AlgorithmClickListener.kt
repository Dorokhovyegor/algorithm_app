package com.dorokhov.androidreviewapp.ui.algorithms

import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel

interface AlgorithmClickListener {
    fun onAlgorithmClick(algorithmModel: AlgorithmModel)
}