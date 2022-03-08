package com.example.ipsatorlocal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ipsatorlocal.R
import com.example.ipsatorlocal.adapter.CrustAdapter
import com.example.ipsatorlocal.adapter.CrustSizeAdapter
import com.example.ipsatorlocal.databinding.CustomPizzaDialogBinding
import com.example.ipsatorlocal.util.*
import com.example.ipsatorlocal.viewmodels.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomizePizzaDialog : DialogFragment() , RecycleViewItemViewClickListener<ListItem> {
    var customPizzaDialogBinding: CustomPizzaDialogBinding? = null

    @Inject
    lateinit var crustAdapter: CrustAdapter
    @Inject
    lateinit var crustSizeAdapter: CrustSizeAdapter

    val homePageVieMode: HomePageViewModel by viewModels(ownerProducer = { requireParentFragment() })
    companion object {
        fun newInstance(): CustomizePizzaDialog {
            return CustomizePizzaDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customPizzaDialogBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.custom_pizza_dialog,
            container,
            false/*, bindingComponent*/
        )
        customPizzaDialogBinding?.rvCrust?.adapter = crustAdapter
        customPizzaDialogBinding?.rvSize?.adapter = crustSizeAdapter
        crustAdapter.setOnClickListener(this as RecycleViewItemViewClickListener<Crust>)
        crustSizeAdapter.setOnClickListener(this as RecycleViewItemViewClickListener<CrustSize>)
        setData()
        customPizzaDialogBinding?.btAddInCart?.setOnClickListener {
            homePageVieMode.addItemInCart( homePageVieMode.selectedCrustId,  homePageVieMode.selectedCrustSizeId,  homePageVieMode.selectedCrustname,  homePageVieMode.selectedCrustSizename,homePageVieMode.selectedPrice)
            dialog?.dismiss()
        }
        return customPizzaDialogBinding?.root
    }

    fun setData() {
        homePageVieMode.pizzaLivedata.observe(requireActivity(), Observer {
            it.data?.let { pizzaDetail ->
                crustAdapter.setItems(pizzaDetail.crusts)
                storeSelectedData(homePageVieMode.selectedCrustId,homePageVieMode.selectedCrustSizeId,homePageVieMode.selectedCrustname,homePageVieMode.selectedCrustSizename,homePageVieMode.selectedPrice)
                crustSizeAdapter.setItems(
                    homePageVieMode.getCorrespondingSelectedCrustSize(
                        pizzaDetail.crusts
                    )
                )
                crustSizeAdapter.getItems().forEach {
                    if (it?.isCrustSizeSelected == true) {
                        showPriceAndCustomPizzaName(it.price)
                    }
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onClickItem(item: ListItem, position: Int) {
        when (item) {
            is Crust -> {
                item.isCrustSelected = true
                crustAdapter.getItems().get(homePageVieMode.lastCrustSelectedPosition)?.isCrustSelected = false
                crustAdapter.notifyItemChanged(homePageVieMode.lastCrustSelectedPosition)
                crustAdapter.notifyItemChanged(position)
                crustSizeAdapter.setItems(homePageVieMode.getCorrespondingSelectedCrustSize(crustAdapter.getItems()))
                homePageVieMode.lastCrustSelectedPosition = position
                storeSelectedData(item.id,homePageVieMode.selectedCrustSizeId,item.name,homePageVieMode.selectedCrustSizename,homePageVieMode.selectedPrice)
                crustSizeAdapter.getItems().forEach {
                    if (it?.isCrustSizeSelected == true) {
                        showPriceAndCustomPizzaName(it.price)
                    }
                }
            }
            is CrustSize -> {
                item.isCrustSizeSelected = true
                crustSizeAdapter.getItems().get(homePageVieMode.lastCrustSizeSelectedPosition)?.isCrustSizeSelected = false
                crustSizeAdapter.notifyItemChanged(homePageVieMode.lastCrustSizeSelectedPosition)
                crustSizeAdapter.notifyItemChanged(position)
                homePageVieMode.lastCrustSizeSelectedPosition = position
                storeSelectedData(homePageVieMode.selectedCrustId,item.id,homePageVieMode.selectedCrustname,item.name,item.price)
                showPriceAndCustomPizzaName(item.price)
            }
        }
    }

   private fun storeSelectedData(crustId:Int?, sizeId:Int?,crustName:String?,crustSizeName: String?,price: Float?){
       homePageVieMode.selectedCrustId=crustId
       homePageVieMode.selectedCrustSizeId=sizeId
       homePageVieMode.selectedCrustname=crustName
       homePageVieMode.selectedCrustSizename=crustSizeName
       homePageVieMode.selectedPrice=price
    }
    fun showPriceAndCustomPizzaName(price: Float?) {
        customPizzaDialogBinding?.tvPrice?.text = price?.toString()
        customPizzaDialogBinding?.tvSelectedPizza?.text=homePageVieMode.selectedCrustname.plus(" , ").plus( homePageVieMode.selectedCrustSizename).plus("   Size")
    }
}