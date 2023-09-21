package com.block.demo.lsemp.model

/**
 * Class to encapsulate network response from the repo layer to UI layer
 */

sealed class NetworkResponse {
    data class Success(
        val statusCode: Int = -1,
        val responseBody: Any? = null
    ) : NetworkResponse()

    data class Failure(
        val statusCode: Int = -1,
        val reason: String = ""
    ) : NetworkResponse()
}
