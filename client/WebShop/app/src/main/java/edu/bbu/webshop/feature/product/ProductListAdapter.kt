package edu.bbu.webshop.feature.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bbu.webshop.R
import edu.bbu.webshop.api.Product

/**
 * Purpose
 * <p>
 * Description
 * <p/>
 * Notes:
 * @author (OPTIONAL! Use only if the code is complex, otherwise delete this line.)
 */
class ProductListAdapter(var products: MutableCollection<Product>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        val name : TextView = view.findViewById(R.id.product_name)
        val price : TextView = view.findViewById(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.elementAt(position)
        holder.name.text = product.name
        holder.price.text = "$ - ${product.price}"
    }

    override fun getItemCount(): Int {
        return products.size
    }
}