package com.dorokhov.androidreviewapp.di.component

import android.app.Application
import com.dorokhov.androidreviewapp.baseui.VmFactoryWrapper
import com.dorokhov.androidreviewapp.component.InterviewApp
import com.dorokhov.androidreviewapp.di.module.*
import com.dorokhov.androidreviewapp.di.wrappers.AppLifecycleWrapper
import com.dorokhov.androidreviewapp.ui.MainActivity
import com.dorokhov.androidreviewapp.ui.TabContainerFragment
import com.dorokhov.androidreviewapp.ui.algorithmdetails.AlgorithmDetailsFragment
import com.dorokhov.androidreviewapp.ui.algorithms.AlgorithmsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        StorageModule::class,
        RepositoryModule::class,
        VmModule::class,
        GlideModule::class,
        NavigationModule::class,
        LocalNavigationModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApp: InterviewApp)
    fun inject(containerFragment: TabContainerFragment)
    fun inject(algorithmsFragment: AlgorithmsFragment)
    fun inject(algorithmDetailsFragment: AlgorithmDetailsFragment)
    fun inject(vmFactoryWrapper: VmFactoryWrapper)
    fun inject(baseApp: AppLifecycleWrapper)
    fun inject(mainActivity: MainActivity)
}