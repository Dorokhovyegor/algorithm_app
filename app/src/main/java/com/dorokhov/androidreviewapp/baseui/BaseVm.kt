package com.dorokhov.androidreviewapp.baseui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseVm : ViewModel() {

    protected val binds: CompositeDisposable = CompositeDisposable()

    protected var vmInited = false

    abstract fun createVmBinds()

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }

    open fun restoreState(bundle: Bundle?) {
        when {
            vmInited.not() && bundle?.isEmpty == false -> {
                onProcessRecreate(bundle)
                createVmBinds()
            }
            vmInited.not() && bundle?.isEmpty != false -> {
                createVmBinds()
            }
        }
        vmInited = true
    }

    open fun onProcessRecreate(bundle: Bundle) {}
}