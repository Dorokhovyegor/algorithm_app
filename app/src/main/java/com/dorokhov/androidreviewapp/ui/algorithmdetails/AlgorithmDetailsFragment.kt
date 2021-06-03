package com.dorokhov.androidreviewapp.ui.algorithmdetails

import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.baseui.BaseFragment
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.frag_algorithm_details.*
import javax.inject.Inject

class AlgorithmDetailsFragment : BaseFragment<AlgorithmDetailsVm>() {

    override val layout: Int
        get() = R.layout.frag_algorithm_details

    @Inject
    lateinit var requestManager: RequestManager

    override fun getVmClass(): Class<AlgorithmDetailsVm> = AlgorithmDetailsVm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentsHolder.appComponent.apply {
            inject(vmFactoryWrapper)
            inject(this@AlgorithmDetailsFragment)
        }
        super.onCreate(savedInstanceState)
    }

    override fun createBinds() {
        super.createBinds()
        vm.getAlgorithm()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ model ->
                setAlgorithmContent(model)
            }, {})
            .addTo(binds)
    }

    private fun setAlgorithmContent(algorithmModel: AlgorithmModel) {
        frag_algorithm_details_toolbar_title.text = algorithmModel.title

        requestManager
            .asGif()
            .load(algorithmModel.image)
            .into(frag_algorithm_details_iv)

        frag_algorithm_details_code_view.setCode(algorithmModel.sourceCode)
        frag_algorithm_full_description.text = algorithmModel.fullDescription
    }
}