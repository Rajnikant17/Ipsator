package com.example.ipsatorlocal.util

import androidx.fragment.app.Fragment
import com.example.ipsatorlocal.ui.BaseActivity

fun Fragment.showLoading() {
    when (activity) {
        is BaseActivity -> (activity as BaseActivity).showLoading()
    }
}

fun Fragment.hideLoading() {
    when (activity) {
        is BaseActivity -> (activity as BaseActivity).hideLoading()
    }
}