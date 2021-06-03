package com.dorokhov.androidreviewapp.baseui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<VM : BaseVm> : Fragment() {

    val vm: VM by lazy {
        ViewModelProvider(this, getVmFactory())
            .get(getVmClass())
    }

    var firstLaunch: Boolean = true

    abstract val layout: Int

    protected abstract fun getVmClass(): Class<VM>

    protected val vmFactoryWrapper = VmFactoryWrapper()

    protected open fun getVmFactory(): ViewModelProvider.Factory = vmFactoryWrapper.factory

    protected val binds: CompositeDisposable = CompositeDisposable()

    // вызывается один раз при первом старте
    open fun init() {}

    // добавлять биндинги, подписки восстановятся
    open fun createBinds() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.restoreState(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createBinds()
    }

    override fun onResume() {
        super.onResume()
        if (firstLaunch) {
            init()
            firstLaunch = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binds.clear()
    }
}