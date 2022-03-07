# Ipsator

Library used :

1 Navigation component
2 Hilt Dependency injection
3 Data binding
4 LiveData
5 ViewBinding
6 Retrofit
7 Coroutine

Used MVVM clean architecture.

App Flow

* FRAGMENTS -> These are the following Fragments used :

1 . Home Fragment - First fragment where we enter the app . In this Fragment we call the api to get the data.
2 . CustomizePizzaDialog Fragment - This fragment is used to customize the pizza and after customizing the pizza there is a "Add to cart" button to add the items in the Cart .
3 . RemovePizzaDialog Fragment - This fragment is used to remove the items added in the cart fragment
4 . CartBottomSheet Fragment - This is a bottom sheet to show the Cart .fragment

* VIEWMODEL -> One common ViewModel i.e HomePageViewModel is being used for all the fragments since the data of all the Fragments are interconnected .
All the data are fetched from this ViewModel across all the Fragments

1. "itemsIncart" is the Mutable list used to store all the items added in the Cart  .
2. "priceAndTotalItemMutalbleLivedata" live data is being used to update the Total item count and Total price .
3. "pizzaLivedata" live data is being used to store the data coming from the App.

* BASE -> BaseFragment and Base Activity is being used to put the common methods or variables such as loader , error showing etc

* BaseDataSource is being created to handle all the response codes such as 500 , 502 , 402 etc . Since here i'm showing a single Toast msg for all errors since its a sample app .

* Hilt Dependency is being used to inject the object across the different classes . There are two dependency module "ApiClientModule" and "PizzaModule"

* Generic class are being used as per the requirement and Data binding is being used to show the data in Adapter such as CrustSize Adapter , Crust Adapter etc .
