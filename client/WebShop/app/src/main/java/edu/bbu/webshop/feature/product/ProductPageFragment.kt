package edu.bbu.webshop.feature.product

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bbu.webshop.R

class ProductPageFragment : Fragment() {

    companion object {
        val ARG_PAGE = "ARG_PAGE"

        fun newInstance(page: Int): ProductPageFragment {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            val fragment = ProductPageFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    val page by lazy {
        arguments.getInt(ARG_PAGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_product, container, false)
        view?.findViewById<TextView>(R.id.page_text)?.text = "Fragment ${page}"
        return view
    }
}