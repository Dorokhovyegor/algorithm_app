package com.dorokhov.androidreviewapp.ui.algorithmdetails

import android.widget.SeekBar
import com.bumptech.glide.RequestManager
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.baseui.BaseFragment
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.extensions.rotateWithAnimation
import com.dorokhov.androidreviewapp.extensions.toVisibleOrGone
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel
import kotlinx.android.synthetic.main.frag_algorithm_details.*
import javax.inject.Inject

class AlgorithmDetailsFragment : BaseFragment<AlgorithmDetailsVm>() {

    override val layout: Int
        get() = R.layout.frag_algorithm_details

    @Inject
    lateinit var requestManager: RequestManager

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

        vm.fullDescriptionVisible.observe(viewLifecycleOwner) { fullDescriptionVisibility ->
            changeDescriptionLayoutParams(fullDescriptionVisibility)
        }

        vm.implementationVisible.observe(viewLifecycleOwner) { implementationVisibility ->
            setImplementationVisibility(implementationVisibility)
        }
    }

    override fun onResume() {
        super.onResume()
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
            vm.changeVisibilityImplementation()
        }

        fad_tv_open_close.setOnClickListener {
            vm.changeVisibilityDescription()
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

    private fun changeDescriptionLayoutParams(fullVisibility: Boolean) {
        if (fullVisibility) {
            frag_algorithm_full_description.maxLines = 100
            fad_tv_open_close.text = getString(R.string.fad_close_text)

        } else {
            frag_algorithm_full_description.maxLines = 5
            fad_tv_open_close.text = getString(R.string.fad_open_text)
            frag_algorithm_details_nsv.scrollTo(
                frag_algorithm_full_description.x.toInt(),
                frag_algorithm_full_description.y.toInt()
            )
        }
    }

    private fun changeTextSize(progress: Int) {
        val newSize = 8f + (progress / 50f) * 8f
        frag_algorithm_details_code_view.textSize = newSize
    }

    private fun setAlgorithmContent(algorithmModel: AlgorithmModel) {
        frag_algorithm_details_toolbar_title.text = algorithmModel.title
        frag_algorithm_details_code_view.text = (algorithmModel.sourceCode)
        frag_algorithm_full_description.text = algorithmModel.shortDescription
    }
}