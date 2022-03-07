package com.example.ipsatorlocal.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PizzaDetail (
    @SerializedName("name")
    @Expose
    val pizzaName:String?,
    @SerializedName("isVeg")
    @Expose
    val isVeg:Boolean?,
    @SerializedName("description")
    @Expose
    val description:String?,
    @SerializedName("defaultCrust")
    @Expose
    val defaultCrust:Int?,
    @SerializedName("crusts")
    @Expose
    val crusts:MutableList<Crust?>
)

data class Crust (
    @SerializedName("id")
    @Expose
    val id:Int?,
    @SerializedName("name")
    @Expose
    var name:String?,
    @SerializedName("defaultSize")
    @Expose
    val defaultSize:Int?,
    @SerializedName("sizes")
    @Expose
    val crustsSize:MutableList<CrustSize?>,
    var isCrustSelected:Boolean?=false
):ListItem

data class CrustSize (
    @SerializedName("id")
    @Expose
    val id:Int?,
    @SerializedName("name")
    @Expose
    val name:String?,
    @SerializedName("price")
    @Expose
    val price:Float?,
    var isCrustSizeSelected:Boolean?=false
):ListItem



data class CartItem(
 val customizePizzaId:String?,
 val customizePizzaName:String?,
 var noOfItem:Int?=0,
 val price:Float?=0.0F
)


data class PriceAndTotalItem(
    var totalnoOfItem:Int?=0,
    val totalprice:Float?=0.0F
)
