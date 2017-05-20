package edu.bbu.webshop.feature

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.bbu.webshop.HomeBinding
import edu.bbu.webshop.R

/**
 * Purpose
 * <p>
 */

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
}