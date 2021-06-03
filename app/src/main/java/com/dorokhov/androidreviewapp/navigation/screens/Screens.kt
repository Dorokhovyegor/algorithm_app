package com.dorokhov.androidreviewapp.navigation.screens

import androidx.fragment.app.Fragment
import com.dorokhov.androidreviewapp.navigation.base.ActivityScreen
import com.dorokhov.androidreviewapp.navigation.base.FragmentScreen
import com.dorokhov.androidreviewapp.ui.SplashActivity
import com.dorokhov.androidreviewapp.ui.TabContainerFragment
import com.dorokhov.androidreviewapp.ui.algorithmdetails.AlgorithmDetailsFragment
import com.dorokhov.androidreviewapp.ui.algorithms.AlgorithmsFragment
import com.dorokhov.androidreviewapp.ui.datastructure.DataStructuresFragment
import com.dorokhov.androidreviewapp.ui.questions.QuestionsFragment

object SplashActivityScreen : ActivityScreen() {
    override val clazz: Class<*> = SplashActivity::class.java
}

class Tab(private val tabName: String) : FragmentScreen() {
    override val fragmentScreen: Fragment
        get() = TabContainerFragment.getNewInstance(tabName)
}

object AlgorithmsScreen : FragmentScreen() {
    override val fragmentScreen: Fragment
        get() = AlgorithmsFragment()
}

object AlgorithmDetailsScreen : FragmentScreen() {
    override val fragmentScreen: Fragment
        get() = AlgorithmDetailsFragment()
}

object DataStructuresScreen : FragmentScreen() {
    override val fragmentScreen: Fragment
        get() = DataStructuresFragment()
}

object QuestionsScreen : FragmentScreen() {
    override val fragmentScreen: Fragment
        get() = QuestionsFragment()
}
