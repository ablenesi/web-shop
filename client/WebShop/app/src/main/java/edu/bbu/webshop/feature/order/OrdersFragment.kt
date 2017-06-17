package edu.bbu.webshop.feature.order

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        WebShopApp.appComponent.inject(this)
        return inflater?.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val recyclerView : RecyclerView? = view?.findViewById<RecyclerView>(R.id.orders_recycler_view)
        recyclerView?.addItemDecoration(DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL))
        orderRepo.getProducts(object : ChangeListener<MutableCollection<Orders>> {
            override fun onChange(var1: MutableCollection<Orders>) {
                recyclerView?.adapter = OrdersAdapter(var1)
            }
            override fun onError(var1: Exception) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }
}

class OrdersViewModel {

}
