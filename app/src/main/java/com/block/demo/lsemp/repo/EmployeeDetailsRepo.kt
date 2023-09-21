package com.block.demo.lsemp.repo

import com.block.demo.lsemp.model.NetworkResponse
import com.block.demo.lsemp.network.service.EmployeeDirService
import kotlinx.coroutines.flow.Flow

/**
 * Repo layer for emp api
 * It can be used to cache the response
 */
class EmployeeDetailsRepo(private val employeeDirService: EmployeeDirService) :
    IEmployeeDetailsRepo {
    override suspend fun getEmployeeList(): Flow<NetworkResponse> {
        return employeeDirService.getEmployees()
    }
}
