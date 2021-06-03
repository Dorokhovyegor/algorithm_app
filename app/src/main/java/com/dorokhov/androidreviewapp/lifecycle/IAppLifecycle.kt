package com.dorokhov.androidreviewapp.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

interface IAppLifecycle : Application.ActivityLifecycleCallbacks {
    val isAppAlive: Boolean
}

class AppLifecycle : IAppLifecycle {
    override val isAppAlive: Boolean
        get() = activityCount > 1

    private var activityCount = 0

    override fun onActivityPaused(p0: Activity) {}

    override fun onActivityStarted(p0: Activity) {}

    override fun onActivityDestroyed(p0: Activity) {
        activityCount--
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

    override fun onActivityStopped(p0: Activity) {}

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        activityCount++
    }

    override fun onActivityResumed(p0: Activity) {}
}