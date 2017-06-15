package edu.bbu.webshop.feature.settings

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import edu.bbu.webshop.R
import edu.bbu.webshop.util.SharedPreferenceManager
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var sharedPreferenceManager : SharedPreferenceManager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //inject the shared pref manager
        return inflater?.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ip : String = view?.findViewById<TextInputEditText>(R.id.server_ip)?.text.toString()
        val port : String = view?.findViewById<TextInputEditText>(R.id.server_port)?.text.toString()
        val button : Button? = view?.findViewById<Button>(R.id.save_button)
        button?.setOnClickListener {
            sharedPreferenceManager.putString(SharedPreferenceManager.SERVER_IP_KEY,ip)
            sharedPreferenceManager.putString(SharedPreferenceManager.SERVER_PORT_KEY,port)
        }
    }
}