package com.dorokhov.androidreviewapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.default.AndroidDefaultContent
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.domain.IAlgorithmRepository
import com.dorokhov.androidreviewapp.navigation.RouterProvider
import com.dorokhov.androidreviewapp.navigation.screens.Tab
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_main.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : FragmentActivity(), RouterProvider {

    @Inject
    override lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentsHolder.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        initBottomNavigationView()

        if (savedInstanceState == null)
            selectTab(ALGORITHM_TAB)
    }

    private fun initBottomNavigationView() {
        act_main_bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_menu_algorithms -> {
                    selectTab(ALGORITHM_TAB)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_menu_data_structures -> {
                    selectTab(DATA_STRUCTURE_TAB)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_menu_questions -> {
                    selectTab(QUESTIONS_TAB)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun selectTab(tab: String) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(tab)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.act_main_fragment_container,
                Tab(tab).fragment, tab
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }

    companion object {
        const val ALGORITHM_TAB = "algorithms"
        const val DATA_STRUCTURE_TAB = "data_structures"
        const val QUESTIONS_TAB = "questions"
    }

}