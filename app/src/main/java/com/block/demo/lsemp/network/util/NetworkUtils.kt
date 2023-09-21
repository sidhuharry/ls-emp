package com.block.demo.lsemp.network.util

import android.util.Log
import com.block.demo.lsemp.model.NetworkResponse
import retrofit2.Response

/**
 * Util class to hold util methods so that other services can re-use.
 */
class NetworkUtils {
    /**
     * The purpose of this method is to save network layer from the effects of changing schemas and stuff
     */
    fun <T> parseResponse(result: Result<Response<T>>): NetworkResponse {
        var networkResp: NetworkResponse = NetworkResponse.Failure()
        if (result.isSuccess) {
            // This means the operation has succeeded not the call.
            // To check call's success, we have to check the http response code.
            result.getOrNull()?.let {
                networkResp = if (it.isSuccessful) {
                    NetworkResponse.Success(it.code(), it.body())
                } else {
                    NetworkResponse.Failure(it.code(), it.message())
                }
            }
        } else {
            Log.e(TAG, "Unable to parse response.", result.exceptionOrNull())
        }
        return networkResp
    }

    companion object {
        private const val TAG = "NetworkUtils"
    }
}
