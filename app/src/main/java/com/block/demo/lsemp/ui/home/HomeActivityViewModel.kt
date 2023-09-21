package com.block.demo.lsemp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.block.demo.lsemp.model.EmployeeNetworkResponse
import com.block.demo.lsemp.model.NetworkResponse
import com.block.demo.lsemp.repo.EmployeeDetailsRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeActivityViewModel(private val empDetailsRepo: EmployeeDetailsRepo) : ViewModel() {

    // private, because you should not expose the MutableLiveData
    private val _isError = MutableLiveData<Boolean>()

    val isError: LiveData<Boolean>
        get() = _isError

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _employees = MutableLiveData<EmployeeNetworkResponse>()

    val employees: LiveData<EmployeeNetworkResponse>
        get() = _employees

    fun loadEmp() {
        viewModelScope.launch {
            _isError.postValue(false)
            _isLoading.postValue(true)

            empDetailsRepo.getEmployeeList().catch { exception ->
                _isError.postValue(true)
                _isLoading.postValue(false)
            }.collect { networkResp ->

                when (networkResp) {
                    is NetworkResponse.Failure -> {
                        _isError.postValue(true)
                        _isLoading.postValue(false)
                    }
                    is NetworkResponse.Success -> {
                        _isLoading.postValue(false)
                        _isError.postValue(false)
                        _employees.postValue(networkResp.responseBody as EmployeeNetworkResponse)
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "HomeActivityViewModel"
    }
}
