package com.dorokhov.androidreviewapp.domain.provider.prng

import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class PRNGProvider
@Inject constructor() : IPRNGProvider {
    override fun provideArrayNumbers(size: Int): Array<Int> {
        val numbers = LinkedList<Int>()
        for (step in 0 until size) {
            numbers.add(Random.nextInt(-size * 2, size * 2))
        }
        return numbers.toTypedArray()
    }
}