package edu.bbu.webshop.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.roughike.bottombar.BottomBar
import edu.bbu.webshop.R
import edu.bbu.webshop.feature.order.OrdersFragment
import edu.bbu.webshop.feature.product.ProductsFragment
import edu.bbu.webshop.feature.settings.SettingsFragment

/**
 * Purpose
 * <p>
 */

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val orders = OrdersFragment()
        val products = ProductsFragment()
        val settings = SettingsFragment()

        findViewById<BottomBar>(R.id.bottom_navigation).setOnTabSelectListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, when (it) {
                        R.id.tab_orders -> orders
                        R.id.tab_products -> products
                        R.id.tab_settings -> settings
                        else -> null
                    })
                    .commit()
        }

    }

}