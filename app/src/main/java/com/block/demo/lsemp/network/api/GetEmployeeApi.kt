package com.block.demo.lsemp.network.api

import com.block.demo.lsemp.model.EmployeeNetworkResponse
import retrofit2.http.GET

interface GetEmployeeApi {

    @GET("sq-mobile-interview/employees.json")
    fun getEmployees(): retrofit2.Call<EmployeeNetworkResponse>
}
