package com.dorokhov.androidreviewapp.ui.algorithms

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.baseui.BaseFragment
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.ui.algorithms.adapter.AlgorithmRecyclerViewAdapter
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmType
import com.dorokhov.androidreviewapp.ui.algorithms.model.BigONotationType
import kotlinx.android.synthetic.main.frag_algorithms.*
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class AlgorithmsFragment : BaseFragment<AlgorithmVm>(), AlgorithmClickListener {

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var rvAdapter: AlgorithmRecyclerViewAdapter

    override val layout: Int
        get() = R.layout.frag_algorithms

    override fun getVmClass() = AlgorithmVm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentsHolder.appComponent.apply {
            inject(vmFactoryWrapper)
            inject(this@AlgorithmsFragment)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = AlgorithmRecyclerViewAdapter(requestManager, this)
        rvAdapter.updateList(AlgorithmFactory.createAlgorithms(15))
        frag_algorithms_rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = rvAdapter
        }
    }

    override fun onAlgorithmClick(algorithmModel: AlgorithmModel) {
        vm.navigateTo()
    }

    override fun createBinds() {

    }
}

object AlgorithmFactory {
    fun createAlgorithms(count: Int): List<AlgorithmModel> {
        val algorithms = LinkedList<AlgorithmModel>()

        fun createAlgorithm(id: Int): AlgorithmModel {
            fun getRandomType(): AlgorithmType {
                return when (Random.nextInt(0, 3)) {
                    0 -> AlgorithmType.SORT
                    1 -> AlgorithmType.SEARCH
                    2 -> AlgorithmType.OTHER
                    else -> AlgorithmType.SORT
                }
            }

            return AlgorithmModel(
                id,
                "Алгоритм ${id}",
                "Короткое описание алгоритма ${id}",
                "",
                "",
                getRandomType(),
                BigONotationType.O_logN,
                ""
            )
        }

        for (i in 1..count) {
            algorithms.add(createAlgorithm(i))
        }
        return algorithms
    }
}