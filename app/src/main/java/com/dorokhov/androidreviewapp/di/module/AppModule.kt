package com.dorokhov.androidreviewapp.di.module

import android.app.Application
import android.content.Context
import com.dorokhov.androidreviewapp.lifecycle.AppLifecycle
import com.dorokhov.androidreviewapp.lifecycle.IAppLifecycle
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideActivityCounter(): IAppLifecycle = AppLifecycle()
}