package edu.bbu.webshop.data.repository

import edu.bbu.webshop.api.Product
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
class ProductRepository @Inject constructor(val api: WebShopService) {

    private val products: MutableMap<Int, Product> = HashMap()

    fun getProducts(changeListener: ChangeListener<MutableCollection<Product>>) {
        if (products.isEmpty()) {
            api.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
                override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
                    Timber.e("Failed to load Products!", t)
                }

                override fun onResponse(call: Call<List<Product>>?, response: Response<List<Product>>?) {
                    Timber.i("Products loaded from API:", response?.body())
                    response?.body()?.forEach { product -> products[product.id] = product }
                    changeListener.onChange(products.values)
                }
            })
        } else {
            changeListener.onChange(products.values)
        }
    }

}