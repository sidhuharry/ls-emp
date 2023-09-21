package com.block.demo.lsemp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.block.demo.lsemp.R
import com.block.demo.lsemp.databinding.ActivityHomeBinding
import com.block.demo.lsemp.model.Employee
import com.block.demo.lsemp.model.EmployeeNetworkResponse
import com.block.demo.lsemp.ui.BaseActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewModel: HomeActivityViewModel

    private lateinit var employeeDetailsListAdapter: EmployeeDetailsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.home_activity_title)
        toolbar.subtitle = getString(R.string.home_activity_subtitle)
        setSupportActionBar(toolbar)

        viewModel = getViewModel()

        viewModel.employees.observe(this, empListObserver)
        viewModel.isLoading.observe(this, loaderObserver)
        viewModel.isError.observe(this, errorObserver)

        employeeDetailsListAdapter = EmployeeDetailsListAdapter { emp -> onEmpItemClick(emp) }
        val citiesRecyclerView: RecyclerView = binding.empListRecyclerView
        citiesRecyclerView.layoutManager = LinearLayoutManager(this)
        citiesRecyclerView.adapter = employeeDetailsListAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEmp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private val errorObserver = Observer<Boolean> { isError ->
        // show error in the UI here
        if (isError) {
            val parentLayout = findViewById<View>(android.R.id.content)
            Snackbar.make(parentLayout, getString(R.string.error_desc_emp), Snackbar.LENGTH_LONG)
                .also {
                    it.setTextMaxLines(4)
                }.show()
        }
    }

    private val loaderObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            binding.progress.show()
        } else {
            binding.progress.hide()
        }
    }

    private val empListObserver = Observer<EmployeeNetworkResponse> { empResp ->

        if (this::employeeDetailsListAdapter.isInitialized) {
            employeeDetailsListAdapter.submitList(empResp.employees)
            employeeDetailsListAdapter.notifyDataSetChanged()
            Log.d(TAG, "Items are ${employeeDetailsListAdapter.itemCount}")
        }
    }

    private fun onEmpItemClick(employee: Employee) {
        // move to detailed with large image and stuff
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}
