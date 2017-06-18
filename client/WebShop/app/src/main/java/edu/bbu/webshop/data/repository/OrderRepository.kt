package edu.bbu.webshop.data.repository

import edu.bbu.webshop.api.Orders
import edu.bbu.webshop.api.WebShopService
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Purpose
 * <p>
 * Description
 * <p/>
 * Notes:
 * @author (OPTIONAL! Use only if the code is complex, otherwise delete this line.)
 */
@Singleton
class OrderRepository @Inject constructor(val api: WebShopService) {

    fun getProducts(changeListener: ChangeListener<MutableCollection<Orders>>) {
        api.getOrders().enqueue(object : retrofit2.Callback<List<Orders>> {
            override fun onFailure(call: Call<List<Orders>>?, t: Throwable?) {
                Timber.e("Failed to load Products!", t)
            }

            override fun onResponse(call: Call<List<Orders>>?, response: Response<List<Orders>>?) {
                Timber.i("Products loaded from API:", response?.body())
                val orders: MutableMap<Int?, Orders> = HashMap()
                response?.body()?.forEach { order -> orders[order.id] = order }
                changeListener.onChange(orders.values)
            }
        })
    }

    fun addOrder(order : Orders, changeListener: ChangeListener<Unit>){
        api.addOrder(order).enqueue(object : retrofit2.Callback<Unit> {
            override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                changeListener.onChange(Unit)

            }

            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
            }

        })
    }
}