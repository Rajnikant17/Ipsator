package com.example.ipsatorlocal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.RemovePizzaItemAdapterBinding
import com.example.ipsatorlocal.util.CartItem
import com.example.ipsatorlocal.util.RecycleViewItemViewClickListener
import javax.inject.Inject

class RemovePizzaAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var attachedToRecyclerView = false
    private var itemClickListener: RecycleViewItemViewClickListener<CartItem>? = null
    private var items= mutableListOf<CartItem?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  RemovePizzaViewHolder(RemovePizzaItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false),parent.context)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            (holder as RemovePizzaViewHolder).bindItem(items[position] as CartItem)
            holder.binding.position=position
            holder.binding.itemClickListener = itemClickListener
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedToRecyclerView = true
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(moreItems: List<CartItem?>) {
        items.clear()
        items.addAll(moreItems)
        if (attachedToRecyclerView) {
            notifyDataSetChanged()
        }
    }

    fun getItems(): MutableList<CartItem?> {
        return items
    }

    fun setOnClickListener(itemClickListener: RecycleViewItemViewClickListener<CartItem>?) {
        itemClickListener?.let {
            this.itemClickListener = it
        }
    }
}