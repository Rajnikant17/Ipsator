package com.example.ipsatorlocal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ipsatorlocal.R
import com.example.ipsatorlocal.adapter.CartBottomSheetAdapter
import com.example.ipsatorlocal.databinding.CartBottomsheetLayoutBinding
import com.example.ipsatorlocal.viewmodels.HomePageViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartBottomSheet : BottomSheetDialogFragment() {
    var cartBottomsheetLayoutBinding: CartBottomsheetLayoutBinding?=null
    val homePageViewModel: HomePageViewModel by viewModels(ownerProducer = { requireParentFragment() })
    @Inject
    lateinit var cartBottomSheetAdapter: CartBottomSheetAdapter
    val homePageVieModel: HomePageViewModel by viewModels(ownerProducer = { requireParentFragment() })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartBottomsheetLayoutBinding=DataBindingUtil.inflate(inflater,R.layout.cart_bottomsheet_layout,container,false)
        cartBottomsheetLayoutBinding?.rvCart?.adapter=cartBottomSheetAdapter
        cartBottomSheetAdapter.setItems(homePageVieModel.itemsIncart)
        homePageViewModel.priceAndTotalIteLivedata.observe(requireActivity(), Observer {
            cartBottomsheetLayoutBinding?.tvTotalItem?.text=getString(R.string.total_items).plus(it?.totalnoOfItem.toString())
            cartBottomsheetLayoutBinding?.tvPrice?.text=getString(R.string.rs).plus(" ").plus(it?.totalprice.toString())
        })
        cartBottomsheetLayoutBinding?.ivDismiss?.setOnClickListener {
            dialog?.dismiss()
        }
        return cartBottomsheetLayoutBinding?.root
    }
}