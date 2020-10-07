package com.mykoatlincoadingsample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mykoatlincoadingsample.R
import com.mykoatlincoadingsample.databinding.ActivityEmployeeListBinding
import com.mykoatlincoadingsample.view_model.EmployeeListViewModel


class EmployeeListActivity : AppCompatActivity() {

    private var mBinding: ActivityEmployeeListBinding? = null
    private var mViewModel: EmployeeListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.emp_list)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_employee_list)
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            EmployeeListViewModel::class.java
        )
        mBinding?.viewModel = mViewModel
        mViewModel?.sampleDao?.getEmployees()?.observe(this, Observer {
            mViewModel?.updateList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel = null
        mBinding?.unbind()
        mBinding = null
    }
}