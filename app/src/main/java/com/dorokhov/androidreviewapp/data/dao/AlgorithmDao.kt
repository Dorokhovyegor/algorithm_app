package com.dorokhov.androidreviewapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dorokhov.androidreviewapp.data.entity.AlgorithmEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlgorithmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: AlgorithmEntity): Completable

    @Query("SELECT * FROM algorithms")
    fun getAlgorithms(): Single<List<AlgorithmEntity>>

    @Query("SELECT * FROM algorithms WHERE id =:id")
    fun getAlgorithm(id: Int): Single<AlgorithmEntity>

    @Query("DELETE from algorithms WHERE id =:id")
    fun deleteAlgorithm(id: Int): Completable

}