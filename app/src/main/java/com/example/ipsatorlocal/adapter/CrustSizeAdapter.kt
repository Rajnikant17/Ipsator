package com.example.ipsatorlocal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.CrustSizeItemBinding
import com.example.ipsatorlocal.util.CrustSize
import com.example.ipsatorlocal.util.RecycleViewItemViewClickListener
import javax.inject.Inject

class CrustSizeAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var attachedToRecyclerView = false
    private var itemClickListener: RecycleViewItemViewClickListener<CrustSize>? = null
    private var itemsList= arrayListOf<CrustSize?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  CrustSizeViewHolder(CrustSizeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),parent.context)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (itemsList.isNotEmpty()) {
            (holder as CrustSizeViewHolder).bindItem(itemsList[position] as CrustSize)
            holder.crustSizeItemBinding.position=position
            holder.crustSizeItemBinding.itemClickListener = itemClickListener
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedToRecyclerView = true
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setItems(moreItems: List<CrustSize?>) {
        itemsList.clear()
        itemsList.addAll(moreItems)
        if (attachedToRecyclerView) {
            notifyDataSetChanged()
        }
    }

    fun getItems(): MutableList<CrustSize?> {
        return itemsList
    }

    fun setOnClickListener(itemClickListener: RecycleViewItemViewClickListener<CrustSize>?) {
        itemClickListener?.let {
            this.itemClickListener = it
        }
    }
}