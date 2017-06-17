package edu.bbu.webshop.feature.product

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bbu.webshop.R

class ProductsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_products, container, false)

        val viewPager = view?.findViewById<ViewPager>(R.id.view_pager)
        viewPager?.adapter = ProductPagerAdapter(fragmentManager)

        val tabLayout = view?.findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout?.setupWithViewPager(viewPager)

        return view
    }
}