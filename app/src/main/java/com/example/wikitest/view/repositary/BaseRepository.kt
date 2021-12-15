package com.example.wikitest.view.repositary

import android.util.Log
import android.view.View
import com.example.wikitest.view.WikiApplication
import com.example.wikitest.view.exaption.NoConnectivityException
import com.example.wikitest.view.service.response.Output
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String, view : View?): T? {
        try {
            val result = apiOutput(call, error)
            var output: T? = null
            when (result) {
                is Output.Success ->{
                    val currentActivity = WikiApplication.applicationContext().getCurrentActivity()
                    Thread {
                        currentActivity?.runOnUiThread {
                            if (view != null) {
//                                view.visibility = View.GONE
                            }
                        }
                    }.start()
                    output = result.output
                }

                is Output.Error -> {
                    val currentActivity = WikiApplication.applicationContext().getCurrentActivity()
                    Thread {
                        currentActivity?.runOnUiThread {
                        }
                    }.start()

                }
            }
            return output
        } catch (noInternet: NoConnectivityException) {
            val currentActivity = WikiApplication.applicationContext().getCurrentActivity()
            if (currentActivity != null) {
            }
            return null
        } catch (e: Exception) {
            e.message?.let { Log.e("Error", it) }
            return null
        }
    }

    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
        error: String
    ): Output<T> {
        val response = call.invoke()
        Log.i("response", response.toString())

        return if (response.isSuccessful) {
            Output.Success(response.body()!!)
        } else  {
            if(response.errorBody() != null) {
                Output.Error(IOException(error))
            } else {
                Output.Error(IOException(error))
            }
        }

    }

}