package com.example.ipsatorlocal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.CrustSizeItemBinding
import com.example.ipsatorlocal.util.CrustSize

class CrustSizeViewHolder (val crustSizeItemBinding: CrustSizeItemBinding, val context: Context) : RecyclerView.ViewHolder(crustSizeItemBinding.root) {
    fun bindItem(item: CrustSize){
        crustSizeItemBinding.crustSize=item
    }
}