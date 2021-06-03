package com.dorokhov.androidreviewapp.domain

import com.dorokhov.androidreviewapp.data.dao.AlgorithmDao
import com.dorokhov.androidreviewapp.data.entity.AlgorithmEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface IAlgorithmRepository {
    fun insetAlgorithm(algorithmEntity: AlgorithmEntity): Completable
    fun getAllAlgorithms(): Single<List<AlgorithmEntity>>
    fun deleteAlgorithm(id: Int): Completable
    fun getAlgorithm(id: Int): Single<AlgorithmEntity>
    fun insertDefaultAlgorithms(algorithmEntity: List<AlgorithmEntity>): Completable
}

class AlgorithmRepository
@Inject constructor(private val algorithmDao: AlgorithmDao) : IAlgorithmRepository {

    override fun insetAlgorithm(algorithmEntity: AlgorithmEntity): Completable {
        return algorithmDao.insert(algorithmEntity)
    }

    override fun getAllAlgorithms(): Single<List<AlgorithmEntity>> {
        return algorithmDao.getAlgorithms()
    }

    override fun getAlgorithm(id: Int): Single<AlgorithmEntity> {
        return algorithmDao.getAlgorithm(id)
    }

    override fun deleteAlgorithm(id: Int): Completable {
        return algorithmDao.deleteAlgorithm(id)
    }

    override fun insertDefaultAlgorithms(algorithmEntity: List<AlgorithmEntity>): Completable {
        return algorithmDao.insert(algorithmEntity.first())
    }
}