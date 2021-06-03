package com.dorokhov.androidreviewapp.di.wrappers

import com.dorokhov.androidreviewapp.lifecycle.IAppLifecycle
import javax.inject.Inject

class AppLifecycleWrapper {
    @Inject
    lateinit var appLifecycle: IAppLifecycle
}