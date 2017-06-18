package edu.bbu.webshop.feature.product

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.api.Category
import edu.bbu.webshop.data.repository.CategoryRepository
import edu.bbu.webshop.data.repository.ChangeListener
import javax.inject.Inject

class ProductPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    @Inject
    lateinit var categoryRepo : CategoryRepository
    private var categories: MutableCollection<Category> = mutableListOf()

    init {
        WebShopApp.appComponent.inject(this)
        categoryRepo.getCategories(object : ChangeListener<MutableCollection<Category>>{
            override fun onChange(var1: MutableCollection<Category>) {
                categories = var1
                notifyDataSetChanged()
            }

            override fun onError(var1: Exception) {

            }
        })
    }

    override fun getItem(position: Int) = ProductPageFragment.newInstance(categories.elementAt(position).id)

    override fun getCount() = categories.size

    override fun getPageTitle(position: Int) = categories.elementAt(position).name

}