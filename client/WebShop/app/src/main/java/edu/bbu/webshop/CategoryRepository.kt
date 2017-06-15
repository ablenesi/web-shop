package edu.bbu.webshop

import edu.bbu.webshop.api.Category
import edu.bbu.webshop.api.WebShopService
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class CategoryRepository @Inject constructor(val api: WebShopService) {

    private val categories: MutableMap<Int, Category> = HashMap()

    fun getCategories(): MutableCollection<Category> {
        if (categories.isEmpty()) {
            api.getCategories().enqueue(object : retrofit2.Callback<List<Category>> {
                override fun onFailure(call: Call<List<Category>>?, t: Throwable?) {
                    Timber.e("Failed to load Categories!", t)
                }

                override fun onResponse(call: Call<List<Category>>?, response: Response<List<Category>>?) {
                    Timber.i("Categories loaded from API:", response?.body())
                    response?.body()?.forEach { category -> categories[category.id] = category }
                }

            })
        }
        return categories.values
    }

}