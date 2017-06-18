package edu.bbu.webshop.feature.product

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bbu.webshop.R
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.api.Product
import edu.bbu.webshop.data.repository.ChangeListener
import edu.bbu.webshop.data.repository.ProductRepository
import javax.inject.Inject

class ProductPageFragment : Fragment() {

    companion object {
        val ARG_CATEGORY_ID = "ARG_CATEGORY_ID"

        fun newInstance(categoryId: Int): ProductPageFragment {
            val args = Bundle()
            args.putInt(ARG_CATEGORY_ID, categoryId)
            val fragment = ProductPageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var productRepo : ProductRepository
    val sum: Int = 0
    val orderedProduct: MutableCollection<Product> = mutableListOf()
    var cardview : CardView? = null

    val categoryID by lazy {
        arguments.getInt(ARG_CATEGORY_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        WebShopApp.appComponent.inject(this)
        val view = inflater?.inflate(R.layout.fragment_product, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val recyclerView : RecyclerView? = view?.findViewById<RecyclerView>(R.id.product_recycler_view)
        recyclerView?.addItemDecoration(DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL))
        cardview = view?.findViewById(R.id.check_out_card)
        productRepo.getProducts(object : ChangeListener<MutableCollection<Product>>{
            override fun onChange(var1: MutableCollection<Product>) {
                recyclerView?.adapter = ProductListAdapter(
                        var1.filter { it.categoryId == categoryID }.toMutableList(),
                        object : ProductListAdapter.Callback{
                            override fun addProduct(product: Product) {
                                cardview?.visibility = View.VISIBLE
                                orderedProduct.add(product)
                                updatePrice()
                            }

                            override fun removeProduct(product: Product) {
                                orderedProduct.remove(product)
                                if(orderedProduct.isEmpty()){
                                    cardview?.visibility = View.GONE
                                }else{
                                    updatePrice()
                                }
                            }
                        })
            }

            override fun onError(var1: Exception) {
                TODO("not implemented")
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    fun updatePrice(){
        view?.findViewById<TextView>(R.id.sum)?.text = "Summary: ${orderedProduct.sumByDouble { it.price }} $"
    }
}