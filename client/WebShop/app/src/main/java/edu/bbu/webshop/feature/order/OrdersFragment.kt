package edu.bbu.webshop.feature.order

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bbu.webshop.R
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.api.Orders
import edu.bbu.webshop.data.repository.ChangeListener
import edu.bbu.webshop.data.repository.OrderRepository
import javax.inject.Inject

class OrdersFragment : LifecycleFragment() {


    private lateinit var viewModel : OrdersViewModel
    @Inject
    lateinit var orderRepo : OrderRepository
    private var orders: MutableCollection<Orders> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WebShopApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        orderRepo.getProducts(object : ChangeListener<MutableCollection<Orders>> {
            override fun onChange(var1: MutableCollection<Orders>) {
                orders = var1
            }

            override fun onError(var1: Exception) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_order, container, false)
    }

}

class OrdersViewModel {

}
