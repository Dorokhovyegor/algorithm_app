package com.dorokhov.androidreviewapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dorokhov.androidreviewapp.data.dao.AlgorithmDao
import com.dorokhov.androidreviewapp.data.entity.AlgorithmEntity

@Database(
    entities = [
        AlgorithmEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class InterviewAppDatabase : RoomDatabase() {

    abstract fun getAlgorithmDao(): AlgorithmDao

}