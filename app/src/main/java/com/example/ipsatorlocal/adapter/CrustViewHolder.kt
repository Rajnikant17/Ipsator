package com.example.ipsatorlocal.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.ipsatorlocal.databinding.CrustItemBinding
import com.example.ipsatorlocal.util.Crust

class CrustViewHolder(val crustItemBinding: CrustItemBinding,val context: Context) : RecyclerView.ViewHolder(crustItemBinding.root) {
    fun bindItem(item:Crust){
        crustItemBinding.crust=item
    }
}