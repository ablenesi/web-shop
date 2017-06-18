package edu.bbu.webshop.feature.product

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bbu.webshop.R
import edu.bbu.webshop.api.Product

class ProductsFragment : Fragment() {

    val orderedProduct: MutableCollection<Product> = mutableListOf()
    var cardview : CardView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_products, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view?.findViewById<ViewPager>(R.id.view_pager)
        viewPager?.adapter = ProductPagerAdapter(childFragmentManager)
        //little bit ugly
        viewPager?.offscreenPageLimit = 20

        val tabLayout = view?.findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout?.setupWithViewPager(viewPager)

        cardview = view?.findViewById(R.id.check_out_card)
    }

    fun addProduct(product: Product){
        cardview?.visibility = View.VISIBLE
        orderedProduct.add(product)
        updatePrice()
    }

    fun removeProduct(product: Product){
        orderedProduct.remove(product)
        if(orderedProduct.isEmpty()){
            cardview?.visibility = View.GONE
        }else{
            updatePrice()
        }
    }


    fun updatePrice(){
        view?.findViewById<TextView>(R.id.sum)?.text = "Summary: ${orderedProduct.sumByDouble { it.price }} $"
    }

    override fun onPause() {
        super.onPause()
        orderedProduct.clear()
    }
}