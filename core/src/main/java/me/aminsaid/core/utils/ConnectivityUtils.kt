package me.aminsaid.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectivityUtils constructor(private val context: Context) {
    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

}