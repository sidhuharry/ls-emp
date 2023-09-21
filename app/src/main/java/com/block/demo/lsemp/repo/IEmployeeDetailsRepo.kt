package com.block.demo.lsemp.repo

import com.block.demo.lsemp.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IEmployeeDetailsRepo {
    suspend fun getEmployeeList(): Flow<NetworkResponse>
}
