package edu.bbu.webshop.feature.product

import android.os.Bundle
import android.support.v4.app.Fragment
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
    private var products: MutableCollection<Product> = mutableListOf()

    val page by lazy {
        arguments.getInt(ARG_PAGE)
    }
    val categoryID by lazy {
        arguments.getInt(ARG_CATEGORY_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WebShopApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        productRepo.getProducts(object : ChangeListener<MutableCollection<Product>>{
            override fun onChange(var1: MutableCollection<Product>) {
                products = var1.filter { it.categoryId == categoryID }.toMutableList()
            }

            override fun onError(var1: Exception) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_product, container, false)
        view?.findViewById<TextView>(R.id.page_text)?.text = "Fragment ${page}"
        return view
    }
}