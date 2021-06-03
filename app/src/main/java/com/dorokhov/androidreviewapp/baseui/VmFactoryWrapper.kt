package com.dorokhov.androidreviewapp.baseui

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class VmFactoryWrapper {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
}