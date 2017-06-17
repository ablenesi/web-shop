package edu.bbu.webshop.feature.product

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bbu.webshop.R
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.api.Product
import edu.bbu.webshop.data.repository.ChangeListener
import edu.bbu.webshop.data.repository.ProductRepository
import javax.inject.Inject

class ProductPageFragment : Fragment() {

    companion object {
        val ARG_PAGE = "ARG_PAGE"
        val ARG_CATEGORY_ID = "ARG_CATEGORY_ID"

        fun newInstance(page: Int, categoryId: Int): ProductPageFragment {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            args.putInt(ARG_CATEGORY_ID, categoryId)
            val fragment = ProductPageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var productRepo : ProductRepository

    val page by lazy {
        arguments.getInt(ARG_PAGE)
    }
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
        productRepo.getProducts(object : ChangeListener<MutableCollection<Product>>{
            override fun onChange(var1: MutableCollection<Product>) {
                recyclerView?.adapter = ProductListAdapter(var1.filter { it.categoryId == categoryID }.toMutableList())
            }

            override fun onError(var1: Exception) {
                TODO("not implemented")
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}