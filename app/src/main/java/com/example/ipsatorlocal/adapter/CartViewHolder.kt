package com.example.ipsatorlocal.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.CartItemAdapterBinding
import com.example.ipsatorlocal.util.CartItem


class CartViewHolder(val cartItemAdapterBinding:CartItemAdapterBinding):RecyclerView.ViewHolder(cartItemAdapterBinding.root) {
    fun bindItem(item: CartItem){
        cartItemAdapterBinding.cartItem=item
    }
}