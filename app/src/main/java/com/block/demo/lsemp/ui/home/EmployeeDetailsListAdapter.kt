package com.block.demo.lsemp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.block.demo.lsemp.R
import com.block.demo.lsemp.model.Employee
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class EmployeeDetailsListAdapter(private val onItemClick: (Employee) -> Unit) :
    ListAdapter<Employee, EmpViewHolder>(EmpDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.emp_list_item, parent, false)
        return EmpViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) {
        val emp = getItem(position)
        holder.bind(emp)
    }
}

class EmpViewHolder(itemView: View, private val onItemClick: (Employee) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val empNameTextView: TextView = itemView.findViewById(R.id.empName)
    private val phoneNum: TextView = itemView.findViewById(R.id.phoneNumber)
    private val emailId: TextView = itemView.findViewById(R.id.emailId)
    private val bio: TextView = itemView.findViewById(R.id.bio)
    private val dp: ImageView = itemView.findViewById(R.id.displayPicture)
    private var currentEmp: Employee? = null

    init {
        itemView.setOnClickListener {
            currentEmp?.let {
                onItemClick(it)
            }
        }
    }

    fun bind(employee: Employee) {
        currentEmp = employee

        empNameTextView.text = employee.fullName
        phoneNum.text = employee.phoneNum
        emailId.text = employee.emailAddress
        bio.text = employee.bio

        val placeholderLoader = CircularProgressDrawable(itemView.context).apply {
            this.centerRadius = 30f
            this.strokeWidth = 5f
            this.start()
        }

        Glide.with(itemView.context)
            .load(employee.photoUrlSmall)
            .placeholder(placeholderLoader)
            .transform(MultiTransformation(RoundedCorners(20)))
            .into(dp)
    }
}

object EmpDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.uuid == newItem.uuid
    }
}
