package com.dorokhov.androidreviewapp.ui.algorithms.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.dorokhov.androidreviewapp.R
import com.dorokhov.androidreviewapp.ui.algorithms.AlgorithmClickListener
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmModel
import com.dorokhov.androidreviewapp.ui.algorithms.model.AlgorithmType
import timber.log.Timber
import javax.inject.Inject

class AlgorithmRecyclerViewAdapter
@Inject constructor(
    val requestManager: RequestManager,
    val algorithmClickListener: AlgorithmClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val diffCallBack = object : DiffUtil.ItemCallback<AlgorithmModel>() {
        override fun areItemsTheSame(oldItem: AlgorithmModel, newItem: AlgorithmModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlgorithmModel, newItem: AlgorithmModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun updateList(newList: List<AlgorithmModel>) {
        differ.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlgorithmVh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_algorithms, parent, false),
            requestManager,
            algorithmClickListener
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlgorithmVh) {
            holder.bind(differ.currentList[position])
        } else {
            throw IllegalStateException("Unknown View Holder")
        }
    }

    override fun getItemCount() = differ.currentList.size

}

class AlgorithmVh(
    val view: View,
    val requestManager: RequestManager,
    val algorithmClickListener: AlgorithmClickListener
) :
    RecyclerView.ViewHolder(view) {

    fun bind(model: AlgorithmModel) = with(view) {
        setOnClickListener {
            Timber.tag("Navigation").i("click")
            algorithmClickListener.onAlgorithmClick(model)
        }
        // get views
        val title = view.findViewById<TextView>(R.id.item_list_algorithms_title)
        val shortDescription =
            view.findViewById<TextView>(R.id.item_list_algorithms_short_description)
        val image = view.findViewById<ImageView>(R.id.item_list_algorithms_iv_type)

        // bind
        when (model.type) {
            AlgorithmType.SORT -> requestManager.load(
                AppCompatResources.getDrawable(
                    view.context,
                    R.drawable.ic_sort
                )
            ).into(image)
            AlgorithmType.SEARCH -> requestManager.load(
                AppCompatResources.getDrawable(
                    view.context,
                    R.drawable.ic_search
                )
            ).into(image)
            AlgorithmType.OTHER -> requestManager.load(
                AppCompatResources.getDrawable(
                    view.context,
                    R.drawable.ic_other_alg
                )
            ).into(image)
        }
        title.text = model.title
        shortDescription.text = model.shortDescription
    }
}