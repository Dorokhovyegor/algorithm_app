package com.dorokhov.androidreviewapp.component

import android.app.Application
import com.dorokhov.androidreviewapp.default.AndroidDefaultContent
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.di.component.DaggerAppComponent
import com.dorokhov.androidreviewapp.domain.IAlgorithmRepository
import com.dorokhov.androidreviewapp.lifecycle.IAppLifecycle
import com.jakewharton.threetenabp.AndroidThreeTen
import io.github.kbiakov.codeview.classifier.CodeProcessor
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class InterviewApp : Application() {
    @Inject
    lateinit var appLifecycle: IAppLifecycle

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
        initComponents()
        CodeProcessor.init(this)
        ComponentsHolder.appComponent.inject(this)
        registerActivityLifecycleCallbacks(appLifecycle)
    }

    private fun initComponents() {
        ComponentsHolder.appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(appLifecycle)
        super.onTerminate()
    }
}