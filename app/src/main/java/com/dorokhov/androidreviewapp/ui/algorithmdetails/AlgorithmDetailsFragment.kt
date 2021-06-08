package com.dorokhov.androidreviewapp.ui.algorithmdetails

import android.widget.SeekBar
import com.bumptech.glide.RequestManager
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.baseui.BaseFragment
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.extensions.rotateWithAnimation
import com.dorokhov.androidreviewapp.extensions.toVisibleOrGone
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

    var implementationIsShown: Boolean = false

    override fun getVmClass(): Class<AlgorithmDetailsVm> = AlgorithmDetailsVm::class.java

    override fun inject() {
        ComponentsHolder.appComponent.apply {
            inject(vmFactoryWrapper)
            inject(this@AlgorithmDetailsFragment)
        }
    }

    override fun createBinds() {
        super.createBinds()
        vm.algorithm.observe(viewLifecycleOwner) {
            setAlgorithmContent(it)
        }
    }

    override fun onResume() {
        super.onResume()
        setImplementationVisibility(implementationIsShown)
        configView()
        initListeners()
    }

    private fun configView() {
        fad_sb_text_size.max = 100
    }

    private fun initListeners() {
        fad_sb_text_size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    changeTextSize(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        fad_btn_show_close.setOnClickListener {
            implementationIsShown = implementationIsShown.not()
            setImplementationVisibility(implementationIsShown)
        }
    }

    private fun setImplementationVisibility(isVisible: Boolean) {
        if (isVisible)
            fad_btn_show_close.rotateWithAnimation(180f, 0f)
        else
            fad_btn_show_close.rotateWithAnimation(0f, 180f)

        fad_hsv.toVisibleOrGone(isVisible)
        fad_sb_text_size.toVisibleOrGone(isVisible)
    }

    private fun changeTextSize(progress: Int) {
        var newSize = if (progress == 0) 8f else 8f * (progress / 50f)
        if (newSize <= 8f) newSize = 8f
        frag_algorithm_details_code_view.textSize = newSize
    }

    private fun setAlgorithmContent(algorithmModel: AlgorithmModel) {
        frag_algorithm_details_toolbar_title.text = algorithmModel.title
        frag_algorithm_details_code_view.text = (algorithmModel.sourceCode)
        frag_algorithm_full_description.text = algorithmModel.fullDescription
    }
}