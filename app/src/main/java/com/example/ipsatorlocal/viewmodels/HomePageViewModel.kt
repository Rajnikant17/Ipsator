package com.example.ipsatorlocal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipsatorlocal.usecases.PizzaDetailUseCase
import com.example.ipsatorlocal.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
@Inject
constructor(val pizzaDetailUseCase: PizzaDetailUseCase):ViewModel() {

    private var pizzaMutalbleLivedata: MutableLiveData<DataState<PizzaDetail?>> = MutableLiveData()
    val pizzaLivedata: LiveData<DataState<PizzaDetail?>>
        get() = pizzaMutalbleLivedata

    private var priceAndTotalItemMutalbleLivedata: MutableLiveData<PriceAndTotalItem> = MutableLiveData()
    val priceAndTotalIteLivedata: LiveData<PriceAndTotalItem>
        get() = priceAndTotalItemMutalbleLivedata

    val itemsIncart= mutableListOf<CartItem?>()
    var lastCrustSelectedPosition=0
    var lastCrustSizeSelectedPosition=0

     var selectedCrustId:Int?=null
     var selectedCrustSizeId:Int?=null
     var selectedCrustname:String?=null
     var selectedCrustSizename:String?=null
     var selectedPrice:Float?=null

     var firstDefaultCrustSize=true

    // Get Data from Api
    fun getPizzaDetail(){
        viewModelScope.launch {
            pizzaDetailUseCase.getPizzaDetail().onEach {
                when(it.data){
                    is  PizzaDetail-> {
                        selectedCrustId = it.data.defaultCrust

                        // storing the default crust

                        it.data.crusts.forEachIndexed { index, crust ->
                            if (crust?.id == selectedCrustId) {
                                crust?.isCrustSelected = true
                                selectedCrustname=crust?.name
                                selectedCrustSizeId = crust?.defaultSize
                                lastCrustSelectedPosition=index
                            }

                            crust?.crustsSize?.forEachIndexed { sizeIndex, crustSize ->
                                if (crustSize?.id == selectedCrustSizeId) {
                                    crustSize?.isCrustSizeSelected = true
                                    if(firstDefaultCrustSize) {
                                        selectedCrustSizename = crustSize?.name
                                        selectedPrice = crustSize?.price
                                        lastCrustSizeSelectedPosition = sizeIndex
                                        firstDefaultCrustSize=false
                                    }
                                }
                            }
                        }
                    }
                }
                pizzaMutalbleLivedata.value=it
            }.launchIn(viewModelScope)
        }
    }

    // Get List of the Crust Size of selected Crust
    fun getCorrespondingSelectedCrustSize(crustList:MutableList<Crust?>):MutableList<CrustSize?>{
        var crustSize= mutableListOf<CrustSize?>()
           crustList.forEach {
                if (it?.isCrustSelected == true) {
                        it.crustsSize.forEachIndexed {sizeIndex, crustSize ->
                            if (crustSize?.isCrustSizeSelected==true) {
                                crustSize.isCrustSizeSelected = true
                                selectedPrice=crustSize.price
                                selectedCrustSizeId=crustSize.id
                                selectedCrustSizename=crustSize.name
                                lastCrustSizeSelectedPosition=sizeIndex
                            }
                        }
                    crustSize=it.crustsSize
                    return crustSize
                }
            }
        return crustSize
    }

     fun addItemInCart(crustId:Int?, sizeId:Int?,crustName:String?,crustSizeName: String?,price: Float?){
        var itemSize=1
         var cartItem:CartItem?=null
        if( itemsIncart.isNotEmpty()) {
            itemsIncart.forEach {
                if(it?.customizePizzaId?.equals(crustId.toString().plus("-").plus(sizeId.toString()))==true){
                    it.noOfItem=it.noOfItem!!+1
                    itemSize=it.noOfItem!!
                    cartItem=it
                }
            }
        }
         if(cartItem ==null)
        itemsIncart.add(CartItem(crustId.toString().plus("-").plus(sizeId),crustName.toString().plus(" , ").plus(crustSizeName).plus("  Size"),itemSize,price))
         calculatePriceAndTotalItem()
    }

   fun calculatePriceAndTotalItem(){
       var totalPrice:Float?=0.0F
       var totalItemAddedInCart:Int?=0
       itemsIncart.forEach {
           totalPrice = totalPrice?.plus(((it?.noOfItem)!!.times((it.price!!))))
           totalItemAddedInCart=totalItemAddedInCart?.plus(it?.noOfItem!!)
       }
       priceAndTotalItemMutalbleLivedata.value=PriceAndTotalItem(totalItemAddedInCart,totalPrice)
    }
}