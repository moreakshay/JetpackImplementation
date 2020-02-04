package moreakshay.com.tmdb.base

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import moreakshay.com.tmdb.base.listeners.NetworkBroadcast
import moreakshay.com.tmdb.common.isNetworkOnline

abstract class BaseActivity : AppCompatActivity(), NetworkBroadcast.NetworkChangeListener {

    lateinit var networkChangeReceiver: NetworkBroadcast
    protected var isNetworkAwailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onNetworkChanged()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver()
    }

    private fun registerReceiver() {
        networkChangeReceiver = NetworkBroadcast()
        networkChangeReceiver.setSharedListener(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    override fun onNetworkChanged() {
        isNetworkAwailable = isNetworkOnline(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }
}
