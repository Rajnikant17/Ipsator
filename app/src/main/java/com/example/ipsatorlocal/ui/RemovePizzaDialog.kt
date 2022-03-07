package com.example.ipsatorlocal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.ipsatorlocal.R
import com.example.ipsatorlocal.adapter.RemovePizzaAdapter
import com.example.ipsatorlocal.databinding.RemovePizzaLayoutBinding
import com.example.ipsatorlocal.util.CartItem
import com.example.ipsatorlocal.util.RecycleViewItemViewClickListener
import com.example.ipsatorlocal.viewmodels.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RemovePizzaDialog : DialogFragment() , RecycleViewItemViewClickListener<CartItem> {
   private var removePizzaBinding:RemovePizzaLayoutBinding?=null
    @Inject
    lateinit var removePizzaAdapter:RemovePizzaAdapter
    val homePageVieModel: HomePageViewModel by viewModels(ownerProducer = { requireParentFragment() })
    companion object {
        fun newInstance(): RemovePizzaDialog {
            return RemovePizzaDialog()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removePizzaBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.remove_pizza_layout,
            container,
            false/*, bindingComponent*/
        )
        removePizzaBinding?.rvPizzaList?.adapter=removePizzaAdapter
        removePizzaAdapter.setItems(homePageVieModel.itemsIncart)
        removePizzaAdapter.setOnClickListener(this)
        removePizzaBinding?.btOk?.setOnClickListener {
            homePageVieModel.calculatePriceAndTotalItem()
            dialog?.dismiss()
        }
        isCancelable=false
        dialog?.setCanceledOnTouchOutside(false)
        return removePizzaBinding?.root
    }

    override fun onClickItem(item: CartItem, position: Int) {
        var itemSize=removePizzaAdapter.getItems().get(position)?.noOfItem
      homePageVieModel.itemsIncart.forEach {
        if(it?.customizePizzaId.equals(item.customizePizzaId)==true){
            it?.noOfItem=it?.noOfItem!!-1
            itemSize=it.noOfItem!!
        }
      }
        removePizzaAdapter.getItems().get(position)?.noOfItem=itemSize
        if(itemSize==0){
            homePageVieModel.itemsIncart.removeAt(position)
            removePizzaAdapter.setItems(homePageVieModel.itemsIncart)
        } else{
            removePizzaAdapter.notifyItemChanged(position)
        }
        if(homePageVieModel.itemsIncart.isEmpty()){
            homePageVieModel.calculatePriceAndTotalItem()
            Toast.makeText(requireActivity(), "All items removed from the cart.", Toast.LENGTH_LONG).show()
            dismiss()
        }
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}