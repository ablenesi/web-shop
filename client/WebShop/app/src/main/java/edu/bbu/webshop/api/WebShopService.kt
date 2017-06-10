package edu.bbu.webshop.api

import android.arch.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

/**
 * REST API access points
 */
interface WebShopService {

    @GET("product")
    fun getProducts(): LiveData<ApiResponse<List<Product>>>

    @GET("category")
    fun getCategories(): LiveData<ApiResponse<List<Category>>>

    @GET("order")
    fun getOrders(): LiveData<ApiResponse<List<Orders>>>

}

data class Category(val id: Int,
                    val name: String)

data class Product(val id: Int,
                   val name: String,
                   val price: Double)

data class Orders(val id: Int,
                  val name: String,
                  val address: String,
                  val date: Long,
                  val orderDetails: List<OrderDetail>)

data class OrderDetail(val product: Product, val count: Int)

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
            code = response.code() as Int
            body = response.body()
        } else {
            TODO("Handle error cases")
        }
    }

}

