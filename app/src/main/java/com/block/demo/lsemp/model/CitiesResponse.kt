package com.block.demo.lsemp.model

import com.google.gson.annotations.SerializedName

data class EmployeeNetworkResponse(
    val employees: List<Employee>
)

data class Employee(
    @SerializedName(value = "uuid") val uuid: String,
    @SerializedName(value = "full_name") val fullName: String,
    @SerializedName(value = "phone_number") val phoneNum: String,
    @SerializedName(value = "email_address") val emailAddress: String,
    @SerializedName(value = "biography") val bio: String,
    @SerializedName(value = "photo_url_small") val photoUrlSmall: String,
    @SerializedName(value = "photo_url_large") val photoUrlLarge: String,
    @SerializedName(value = "team") val team: String,
    @SerializedName(value = "employee_type") val empType: EmpType
)

enum class EmpType {
    FULL_TIME,
    PART_TIME
}
