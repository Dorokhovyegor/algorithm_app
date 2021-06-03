package com.dorokhov.androidreviewapp.ui.mapper

import com.dorokhov.androidreviewapp.data.entity.AlgorithmEntity
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmType
import com.dorokhov.androidreviewapp.ui.algorithms.model.BigONotationType

object AlgorithmMapper {
    fun convertToPresentation(algorithmEntity: AlgorithmEntity): AlgorithmModel {
        val algorithmType = when (algorithmEntity.algorithmType) {
            0 -> AlgorithmType.SORT
            1 -> AlgorithmType.SEARCH
            2 -> AlgorithmType.OTHER
            else -> throw IllegalStateException("Unknown algorithm type")
        }

        val bigOType = when (algorithmEntity.bigONotationType) {
            0 -> BigONotationType.O_1
            1 -> BigONotationType.O_sqrtN
            2 -> BigONotationType.O_logN
            3 -> BigONotationType.O_NlogN
            4 -> BigONotationType.O_sqrN
            5 -> BigONotationType.O_other
            else -> throw IllegalStateException("Unknown big O notation type")
        }

        return AlgorithmModel(
            algorithmEntity.id.toInt(),
            algorithmEntity.title,
            algorithmEntity.shortDescription,
            algorithmEntity.fullDescription,
            algorithmEntity.sourceCode,
            algorithmType,
            bigOType,
            algorithmEntity.image)
    }
}