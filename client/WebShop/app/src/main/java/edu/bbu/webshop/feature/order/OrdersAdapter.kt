package edu.bbu.webshop.feature.order

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bbu.webshop.R
import edu.bbu.webshop.api.Orders

/**
 * Purpose
 *
 *
 * Description
 *
 *
 * Notes:

 * @author (OPTIONAL! Use only if the code is complex, otherwise delete this line.)
 */
class OrdersAdapter(var orders: MutableCollection<Orders>) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        val name : TextView = view.findViewById(R.id.order_name)
        val date : TextView = view.findViewById(R.id.order_date)
        val address : TextView = view.findViewById(R.id.order_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders.elementAt(position)
        holder.name.text = order.name
        holder.address.text = order.address
        holder.date.text = order.date
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}