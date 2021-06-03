package com.dorokhov.androidreviewapp.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dorokhov.androidreviewapp.data.InterviewAppDatabase
import com.dorokhov.androidreviewapp.data.dao.AlgorithmDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): RoomDatabase =
        Room.databaseBuilder(context, InterviewAppDatabase::class.java, "main_data_base")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideBatteryStateDao(db: RoomDatabase): AlgorithmDao {
        return (db as InterviewAppDatabase).getAlgorithmDao()
    }

}