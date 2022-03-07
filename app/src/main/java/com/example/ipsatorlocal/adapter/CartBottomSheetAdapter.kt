package com.example.ipsatorlocal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.CartItemAdapterBinding
import com.example.ipsatorlocal.util.CartItem
import javax.inject.Inject

class CartBottomSheetAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var attachedToRecyclerView = false
    private var items= mutableListOf<CartItem?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  CartViewHolder(CartItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            (holder as CartViewHolder).bindItem(items[position] as CartItem)
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
        items.addAll(moreItems)
        if (attachedToRecyclerView) {
            notifyDataSetChanged()
        }
    }

}