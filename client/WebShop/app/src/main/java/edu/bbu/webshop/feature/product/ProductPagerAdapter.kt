package edu.bbu.webshop.feature.product

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import edu.bbu.webshop.CategoryRepository
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.api.Category
import javax.inject.Inject

class ProductPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    @Inject
    lateinit var categoryRepo : CategoryRepository
    private var categories: MutableCollection<Category> = mutableListOf()

    init {
        WebShopApp.appComponent.inject(this)
        categories = categoryRepo.getCategories()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int) = ProductPageFragment.newInstance(position)

    override fun getCount() = categories.size

    override fun getPageTitle(position: Int) = categories.elementAt(position).name

}