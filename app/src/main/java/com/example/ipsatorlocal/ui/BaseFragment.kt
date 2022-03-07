package com.example.ipsatorlocal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ipsatorlocal.util.DataState
import com.example.ipsatorlocal.util.hideLoading
import com.example.ipsatorlocal.util.showLoading

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    @get:LayoutRes
    abstract val fragmentLayoutResId: Int
    protected var binding: VDB ?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayoutResId, container, false/*, bindingComponent*/)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.lifecycleOwner = this
        }
        loadData()
    }

    abstract fun loadData()

    open val observer = Observer<DataState<*>> {
        when (it) {
            is DataState.Loading -> {
                showLoading()
            }
            is DataState.Error<*> -> {
                // Different error codes has been handled in BaseDataSource class .
                showToast("Something went wrong plzz try again or plz check Internet")
                hideLoading()
            }
            is DataState.Success<*> -> {
                hideLoading()
                receivedResponse(it.data)

            }
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
    abstract fun receivedResponse(item: Any?)
}