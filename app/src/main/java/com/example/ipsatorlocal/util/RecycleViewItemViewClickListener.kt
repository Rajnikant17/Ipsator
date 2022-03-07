package com.example.ipsatorlocal.util

interface RecycleViewItemViewClickListener<T> {
    fun onClickItem(item: T,position:Int)
}