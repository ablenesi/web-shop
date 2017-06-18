package edu.bbu.webshop.feature.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import edu.bbu.webshop.R
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.api.OrderDetail
import edu.bbu.webshop.api.Orders
import edu.bbu.webshop.api.Product
import edu.bbu.webshop.data.repository.ChangeListener
import edu.bbu.webshop.data.repository.OrderRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ProductsFragment : Fragment() {

    val orderedProduct: MutableCollection<Product> = mutableListOf()
    @Inject
    lateinit var orderRepository: OrderRepository

    var orderButton : Button? = null
    var  cardview : CardView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        WebShopApp.appComponent.inject(this)
        return inflater?.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        cardview = view?.findViewById<CardView>(R.id.check_out_card)
        orderButton = view?.findViewById<Button>(R.id.send_order_btn)

        orderButton?.setOnClickListener {
            val orderDetails: MutableCollection<OrderDetail> = mutableListOf()
            val productIds = orderedProduct.groupBy { it.id }.keys
            productIds.forEach {
                val id = it
                orderDetails.add(OrderDetail(
                        productId = id,
                        quantity = orderedProduct.count { it.id == id }
                ))
            }

            @SuppressLint("SimpleDateFormat")
            val order: Orders = Orders(
                    name = "Order -" + this.hashCode(),
                    address = "New York, John F. Kennedy street 5.",
                    date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()).replace(" ", "T"),
                    orderDetails = orderDetails
            )

            orderRepository.addOrder(order, object : ChangeListener<Unit> {
                override fun onChange(var1: Unit) {
                    Toast.makeText(view?.context, "Order sent", Toast.LENGTH_SHORT).show()
                    cardview?.visibility = View.GONE
                    orderedProduct.clear()
                    setViewPager()
                }

                override fun onError(var1: Exception) {

                }

            })
        }
    }

    fun setViewPager() {
        val viewPager = view?.findViewById<ViewPager>(R.id.view_pager)
        viewPager?.adapter = ProductPagerAdapter(childFragmentManager)
        //little bit ugly
        viewPager?.offscreenPageLimit = 20
        val tabLayout = view?.findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout?.setupWithViewPager(viewPager)
    }

    fun addProduct(product: Product) {
        cardview?.visibility = View.VISIBLE
        orderedProduct.add(product)
        updatePrice()
    }

    fun removeProduct(product: Product) {
        orderedProduct.remove(product)
        if (orderedProduct.isEmpty()) {
            cardview?.visibility = View.GONE
        } else {
            updatePrice()
        }
    }

    fun updatePrice() {
        view?.findViewById<TextView>(R.id.sum)?.text = "Summary: ${orderedProduct.sumByDouble { it.price }} $"
    }

    override fun onPause() {
        super.onPause()
        orderedProduct.clear()
    }
}
