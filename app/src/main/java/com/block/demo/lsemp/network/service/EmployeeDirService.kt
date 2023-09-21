package com.block.demo.lsemp.network.service

import com.block.demo.lsemp.model.NetworkResponse
import com.block.demo.lsemp.network.api.GetEmployeeApi
import com.block.demo.lsemp.network.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EmployeeDirService(
    private val employeeDetailsApi: GetEmployeeApi,
    private val networkUtils: NetworkUtils
) : IEmployeeDirService {

    companion object {
        private const val TAG = "EmployeeDirService"
    }

    override fun getEmployees(): Flow<NetworkResponse> {
        return flow {
            val result = kotlin.runCatching {
                employeeDetailsApi.getEmployees().execute()
            }
            val networkResponse = networkUtils.parseResponse(result)
            emit(networkResponse)
        }.flowOn(Dispatchers.IO)
    }
}
