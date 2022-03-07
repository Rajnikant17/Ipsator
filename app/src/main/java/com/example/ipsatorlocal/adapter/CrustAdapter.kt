package com.example.ipsatorlocal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.CrustItemBinding
import com.example.ipsatorlocal.util.Crust
import com.example.ipsatorlocal.util.RecycleViewItemViewClickListener
import javax.inject.Inject

class CrustAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var attachedToRecyclerView = false
    private var itemClickListener: RecycleViewItemViewClickListener<Crust>? = null
    private var itemsList = arrayListOf<Crust?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  CrustViewHolder(CrustItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),parent.context)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (itemsList.isNotEmpty()) {
            (holder as CrustViewHolder).bindItem(itemsList[position] as Crust)
            holder.crustItemBinding.position=position
            holder.crustItemBinding.itemClickListener = itemClickListener
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedToRecyclerView = true
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setItems(moreItems: List<Crust?>) {
        itemsList.addAll(moreItems)
        if (attachedToRecyclerView) {
            notifyDataSetChanged()
        }
    }

    fun getItems(): MutableList<Crust?> {
        return itemsList
    }

    fun setOnClickListener(itemClickListener: RecycleViewItemViewClickListener<Crust>?) {
        itemClickListener?.let {
            this.itemClickListener = it
        }
    }
}