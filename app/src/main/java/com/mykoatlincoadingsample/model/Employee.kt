package com.mykoatlincoadingsample.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mykoatlincoadingsample.room.RoomDetails.AGE
import com.mykoatlincoadingsample.room.RoomDetails.EMPLOYEE
import com.mykoatlincoadingsample.room.RoomDetails.NAME
import com.mykoatlincoadingsample.room.RoomDetails.SALARY

/*@description It i just model class used to pass the employee data and also worked as an  entity for the DB*/
@Entity(tableName = EMPLOYEE)
class Employee {
    @NonNull
    @PrimaryKey
    var id: String? = null

    @ColumnInfo(name = NAME)
    var employee_name: String? = null

    @ColumnInfo(name = SALARY)
    var employee_salary: String? = null

    @ColumnInfo(name = AGE)
    var employee_age: String? = null
}