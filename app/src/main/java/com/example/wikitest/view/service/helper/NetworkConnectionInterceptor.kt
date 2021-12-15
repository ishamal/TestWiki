package com.example.wikitest.view.service.helper

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
//        if (!isConnected()) {
//            throw NoConnectivityException()
//        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

//    fun isConnected(): Boolean {
//        val connectivityManager =
//            Shifts.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            @Suppress("DEPRECATION")
//            val netInfo = connectivityManager.activeNetworkInfo
//            if (netInfo != null) {
//                @Suppress("DEPRECATION")
//                return (netInfo.isConnectedOrConnecting && (netInfo.type == ConnectivityManager.TYPE_WIFI || netInfo.type == ConnectivityManager.TYPE_MOBILE))
//            }
//        } else {
//            val network = connectivityManager.activeNetwork
//            if (network != null) {
//                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
//                if (networkCapabilities != null) {
//                    return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(
//                        NetworkCapabilities.TRANSPORT_WIFI
//                    ))
//                }
//            }
//        }
//        return false
//    }

}