package com.dorokhov.androidreviewapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dorokhov.androidreviewapp.di.vm.VmFactory
import com.dorokhov.androidreviewapp.di.vm.VmKeyName
import com.dorokhov.androidreviewapp.ui.algorithmdetails.AlgorithmDetailsVm
import com.dorokhov.androidreviewapp.ui.algorithms.AlgorithmVm
import com.dorokhov.androidreviewapp.ui.sandbox.sort.SandboxSortVm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface VmModule {

    @Binds
    @IntoMap
    @VmKeyName(AlgorithmVm::class)
    fun bindUpgradeAlgorithmVm(viewModel: AlgorithmVm): ViewModel

    @Binds
    @IntoMap
    @VmKeyName(AlgorithmDetailsVm::class)
    fun bindUpgradeAlgorithmDetailsVm(viewModel: AlgorithmDetailsVm): ViewModel

    @Binds
    @IntoMap
    @VmKeyName(SandboxSortVm::class)
    fun bindSortVm(viewModel: SandboxSortVm): ViewModel

    @Binds
    fun provideVmFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}