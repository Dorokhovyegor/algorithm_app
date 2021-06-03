package com.dorokhov.androidreviewapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AlgorithmType 0 - SORT, 1 - SEARCH, 2 - OTHER
 * BigONotationType 0 - O_1, 1 - O_sqrtN, 2 - O_logN, 3 - O_NlogN, 4 - O_sqrN, 5 - O_other
 * */
@Entity(tableName = "algorithms")
data class AlgorithmEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String,
    @ColumnInfo(name = "fullDescription")
    val fullDescription: String,
    @ColumnInfo(name = "sourceCode")
    val sourceCode: String,
    @ColumnInfo(name = "algorithmType")
    val algorithmType: Int,
    @ColumnInfo(name = "bigONotationType")
    val bigONotationType: Int,
    @ColumnInfo(name = "image")
    val image: String
)