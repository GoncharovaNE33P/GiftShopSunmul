package com.example.giftshopsunmulapp.domain.utlis

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkMonitor(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isConnected = MutableStateFlow(checkNetwork())

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isConnected.value = true
        }

        override fun onLost(network: Network) {
            isConnected.value = false
        }
    }

    init {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    private fun checkNetwork(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun unregisterCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}