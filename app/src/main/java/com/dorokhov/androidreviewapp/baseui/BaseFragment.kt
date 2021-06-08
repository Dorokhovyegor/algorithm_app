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

    /**
     * Установка layout
     * */
    abstract val layout: Int

    /**
     * Все подписки во вью оказываются здесь, отчищается в onDestroyView()
     * */
    protected val binds: CompositeDisposable = CompositeDisposable()

    protected val vmFactoryWrapper = VmFactoryWrapper()

    protected abstract fun getVmClass(): Class<VM>

    protected open fun getVmFactory(): ViewModelProvider.Factory = vmFactoryWrapper.factory

    /**
     * Для внедрения зависисомтей использовать только эту функцию
     * */
    abstract fun inject()

    /**
     * Вызывается один раз при первом старте
     */
    open fun onFirstStart() {}

    /**
     * Добавление биндингов
     * */
    open fun createBinds() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
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
            onFirstStart()
            firstLaunch = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binds.clear()
    }
}