package com.dorokhov.androidreviewapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.navigation.BackButtonListener
import com.dorokhov.androidreviewapp.navigation.RouterProvider
import com.dorokhov.androidreviewapp.navigation.base.FragmentScreen
import com.dorokhov.androidreviewapp.navigation.screens.AlgorithmsScreen
import com.dorokhov.androidreviewapp.navigation.screens.DataStructuresScreen
import com.dorokhov.androidreviewapp.navigation.screens.QuestionsScreen
import com.dorokhov.androidreviewapp.navigation.subnavigation.LocalCiceroneHolder
import com.dorokhov.androidreviewapp.ui.MainActivity.Companion.ALGORITHM_TAB
import com.dorokhov.androidreviewapp.ui.MainActivity.Companion.DATA_STRUCTURE_TAB
import com.dorokhov.androidreviewapp.ui.MainActivity.Companion.QUESTIONS_TAB
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class TabContainerFragment : Fragment(), RouterProvider, BackButtonListener {

    private val navigator: Navigator by lazy {
        SupportAppNavigator(requireActivity(), childFragmentManager, R.id.ftc_container)
    }

    private val containerName: String
        get() = requireArguments().getString(EXTRA_NAME) ?: throw IllegalStateException()

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentsHolder.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(containerName)

    override val router: Router
        get() = cicerone.router

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.ftc_container)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()) {
            true
        } else {
            (activity as RouterProvider?)!!.router.exit()
            true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            cicerone.router.replaceScreen(getScreenFromString(containerName))
        }
    }

    private fun getScreenFromString(tag: String): FragmentScreen {
        return when (tag) {
            ALGORITHM_TAB -> AlgorithmsScreen
            DATA_STRUCTURE_TAB -> DataStructuresScreen
            QUESTIONS_TAB -> QuestionsScreen
            else -> throw java.lang.IllegalStateException()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_tab_container, container, false)
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    companion object {
        private const val EXTRA_NAME = "tcf_extra_name"

        fun getNewInstance(name: String?) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                }
            }
    }
}