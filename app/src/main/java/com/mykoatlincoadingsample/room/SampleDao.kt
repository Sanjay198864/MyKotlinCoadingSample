package com.mykoatlincoadingsample.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mykoatlincoadingsample.model.Employee

@Dao
interface SampleDao {

    @Query("SELECT * from Employee")
    fun getEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employees: List<Employee>)

    @Query("DELETE FROM Employee")
    suspend fun deleteAll()
}