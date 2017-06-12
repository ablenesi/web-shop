package edu.bbu.webshop.feature.product

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ProductPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = ProductPageFragment.newInstance(position)

    override fun getCount() = 3

    override fun getPageTitle(position: Int) = "Category $position"

}