package com.example.ipsatorlocal.ui

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ipsatorlocal.R
import com.example.ipsatorlocal.databinding.HomeLayoutBinding
import com.example.ipsatorlocal.util.PizzaDetail
import com.example.ipsatorlocal.viewmodels.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeLayoutBinding>() {
    override val fragmentLayoutResId: Int
        get() = R.layout.home_layout
    val homePageViewModel:HomePageViewModel by viewModels(ownerProducer = { this })
    override fun loadData() {
        homePageViewModel.getPizzaDetail()
        homePageViewModel.pizzaLivedata.observe(requireActivity(),observer)
        observePriceAndItemchange()
        onClick()
    }
    private fun observePriceAndItemchange(){
        homePageViewModel.priceAndTotalIteLivedata.observe(requireActivity(), Observer {
            binding?.tvTotalItem?.text=getString(R.string.total_items).plus(it?.totalnoOfItem.toString())
            binding?.tvPrice?.text=getString(R.string.rs).plus(" ").plus(it?.totalprice.toString())
            if(homePageViewModel.itemsIncart.isNotEmpty()){
                binding?.cvRemovePizzaFromCart?.visibility=View.VISIBLE
                binding?.rlShowCart?.visibility=View.VISIBLE
            }else{
                binding?.cvRemovePizzaFromCart?.visibility=View.GONE
                binding?.rlShowCart?.visibility=View.GONE
            }
        })
    }
  private  fun onClick(){
        binding?.cvCustomizePizza?.setOnClickListener {
            CustomizePizzaDialog.newInstance().show(childFragmentManager, "")
        }
        binding?.cvRemovePizzaFromCart?.setOnClickListener {
            RemovePizzaDialog.newInstance().show(childFragmentManager, "")
        }
        binding?.rlShowCart?.setOnClickListener {
            CartBottomSheet().show(childFragmentManager, "")
        }
    }
    override fun receivedResponse(item: Any?) {
   item?.let {
       when(it){
          is PizzaDetail -> {
              binding?.cvCustomizePizza?.visibility=View.VISIBLE
              binding?.pizzaName?.text= it.pizzaName
              binding?.pizzaDes?.text=it.description
              binding?.ivPiza?.visibility=View.VISIBLE
              binding?.dot?.visibility=View.VISIBLE
              if(it.isVeg==true){
                  binding?.dot?.setTextColor(ContextCompat.getColor(requireContext(),R.color.green))
              }else{
                  binding?.dot?.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
              }
          }
           else ->{
          }
       }
   }
    }
}