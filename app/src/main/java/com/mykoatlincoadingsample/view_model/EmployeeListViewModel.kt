package com.mykoatlincoadingsample.view_model


import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mykoatlincoadingsample.R
import com.mykoatlincoadingsample.connectivity.ApiProcessor
import com.mykoatlincoadingsample.connectivity.RestDetails.SUCCESS
import com.mykoatlincoadingsample.model.Employee
import com.mykoatlincoadingsample.room.SampleDao
import com.mykoatlincoadingsample.room.SampleRoomDatabase
import com.mykoatlincoadingsample.view.helper.EmployeeListAdapter
import kotlinx.coroutines.launch

/*@description This ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
 It allows data to survive configuration changes such as screen rotations.*/
class EmployeeListViewModel(application: Application) : AndroidViewModel(application) {
    val mAdapter: EmployeeListAdapter = EmployeeListAdapter()
    val mStatus: ObservableField<String> =
        ObservableField(application.getString(R.string.processing_wait))
    val sampleDao: SampleDao = SampleRoomDatabase.getDatabase(application).sampleDao()

    init {
        viewModelScope.launch {
            mStatus.set(ApiProcessor.getEmployees(application.applicationContext, sampleDao))
        }
    }

    /*@description  Used to update the list*/
    fun updateList(employees: List<Employee>) {
        mAdapter.updateList(employees)
        if (employees.isNotEmpty()) {
            mStatus.set(SUCCESS)
        }
    }
}