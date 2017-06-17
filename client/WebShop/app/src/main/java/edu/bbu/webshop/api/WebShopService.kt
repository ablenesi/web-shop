package edu.bbu.webshop.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * REST API access points
 */
interface WebShopService {

    @GET("product")
    fun getProducts(): Call<List<Product>>

    @GET("category")
    fun getCategories(): Call<List<Category>>

    @GET("order")
    fun getOrders(): Call<List<Orders>>

}

data class Category(@SerializedName("Id") val id: Int,
                    @SerializedName("Name") val name: String)

data class Product(@SerializedName("Id") val id: Int,
                   @SerializedName("Name") val name: String,
                   @SerializedName("Price") val price: Double,
                   @SerializedName("CategoryId") val categoryId: Int)

data class Orders(@SerializedName("Id") val id: Int,
                  @SerializedName("Name") val name: String,
                  @SerializedName("Address") val address: String,
                  @SerializedName("Date") val date: String,
                  @SerializedName("OrderDetails") val orderDetails: List<OrderDetail>)

data class OrderDetail(@SerializedName("ProductId") val productId: Int,
                       @SerializedName("OrderId") val orderId: Int,
                       @SerializedName("Quantity") val quantity: Int)

/**
 * Request response representation
 */
class ApiResponse<T> {
    var code: Int = -1
    var message: String? = null
    var body: T? = null

    constructor(t: Throwable?) {
        message = t?.message
    }

    constructor(response: Response<T>?) {
        if (response != null && response.isSuccessful) {
            code = response.code()
            body = response.body()
        } else {
            TODO("Handle error cases")
        }
    }
}

