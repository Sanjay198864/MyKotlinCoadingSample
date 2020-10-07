@file:Suppress("DEPRECATION")

package com.mykoatlincoadingsample.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.mykoatlincoadingsample.R
import com.mykoatlincoadingsample.room.SampleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/*---- Holds all suspended methods which is using for API calling*/
object ApiProcessor {

    /**
     * @description method to get the list of employees from tbe cloud API and insert in the local db
     */
    suspend fun getEmployees(context: Context?, sampleDao: SampleDao): String {
        if (!isNetworkAvailable(context)) {
            return context!!.getString(R.string.no_connection_available)
        }
        val result =
            withContext(Dispatchers.IO) {
                ApiInterface().getEmployeeList(RestDetails.EMPLOYEES)
            }
        if (result.isSuccessful) {
            result.body()?.let {
                withContext(Dispatchers.IO) {
                    sampleDao.deleteAll()
                    sampleDao.insert(result.body()!!.data!!)
                }
            }
            return RestDetails.SUCCESS
        }
        return result.message()
    }

    /**
     * @description method to check for network availability
     */
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context?): Boolean {
        val activeNetwork: NetworkInfo?
        val cm =
            Objects.requireNonNull<Context>(context)
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}