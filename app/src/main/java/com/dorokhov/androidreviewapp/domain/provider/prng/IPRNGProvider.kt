package com.dorokhov.androidreviewapp.domain.provider.prng

interface IPRNGProvider {
    fun provideArrayNumbers(size: Int): Array<Int>
}