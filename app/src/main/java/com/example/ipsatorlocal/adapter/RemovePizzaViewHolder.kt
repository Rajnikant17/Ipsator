package com.example.ipsatorlocal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.RemovePizzaItemAdapterBinding
import com.example.ipsatorlocal.util.CartItem

class RemovePizzaViewHolder(val binding: RemovePizzaItemAdapterBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bindItem(item: CartItem){
        binding.cartItem=item
    }
}