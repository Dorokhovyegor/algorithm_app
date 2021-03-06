package com.dorokhov.androidreviewapp.ui.sandbox.sort

import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.baseui.BaseFragment
import com.dorokhov.androidreviewapp.di.ComponentsHolder
import com.dorokhov.androidreviewapp.extensions.toVisibleOrGone
import kotlinx.android.synthetic.main.frag_sand_box_sort.*

class SandboxSortFragment : BaseFragment<SandboxSortVm>() {

    override val layout: Int
        get() = R.layout.frag_sand_box_sort

    override fun getVmClass(): Class<SandboxSortVm> = SandboxSortVm::class.java

    override fun inject() {
        ComponentsHolder.appComponent.apply {
            inject(this@SandboxSortFragment)
            inject(vmFactoryWrapper)
        }
    }

    override fun createBinds() {
        super.createBinds()
        vm.logs.observe(viewLifecycleOwner) { logs ->
            fsbd_tv_logs.text = logs
        }

        vm.numbers.observe(viewLifecycleOwner) { generatedNumbers ->
            if (generatedNumbers != null && generatedNumbers.size > 20) {
                fsbd_et_elements.setText("Слишком много элементов для показа (количетсво элементов ${generatedNumbers.size})")
            } else {
                if (generatedNumbers != null) {
                    StringBuilder().apply {
                        for (number in generatedNumbers) {
                            this.append("$number,")
                        }
                    }.also {
                        fsbd_et_elements.text = (it.toString().take(it.toString().length - 1))
                    }
                } else {
                    fsbd_et_elements.text = ""
                }
            }
        }

        vm.isLoading.observe(viewLifecycleOwner) { isLoading ->
            fsds_root_loading.toVisibleOrGone(isLoading)
        }
    }

    override fun onResume() {
        super.onResume()
        initListeners()
    }

    private fun initListeners() {
        fsbd_btn_generate.setOnClickListener {
            vm.generateNumbers(getSize())
        }

        fsbd_btn_sort.setOnClickListener {
            vm.startSort()
        }

        fsbd_btn_reset.setOnClickListener {
            vm.reset()
        }
    }

    private fun getSize(): Int {
        return fsbd_et_count_of_numbers.text.toString().toIntOrNull() ?: 10
    }

}
