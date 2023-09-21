package com.block.demo.lsemp.network.service

import com.block.demo.lsemp.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IEmployeeDirService {

    fun getEmployees(): Flow<NetworkResponse>
}
