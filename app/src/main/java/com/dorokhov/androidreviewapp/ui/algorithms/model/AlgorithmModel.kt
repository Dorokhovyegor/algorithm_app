package com.dorokhov.androidreviewapp.ui.algorithms.model

data class AlgorithmModel(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val fullDescription: String,
    val sourceCode: String,
    val type: AlgorithmType,
    val bigONotation: BigONotationType,
    val image: String
)

enum class AlgorithmType {
    SORT, SEARCH, OTHER
}

enum class BigONotationType {
    O_1,
    O_sqrtN,
    O_logN,
    O_NlogN,
    O_sqrN,
    O_other
}