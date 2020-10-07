package com.mykoatlincoadingsample.view.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mykoatlincoadingsample.R
import com.mykoatlincoadingsample.databinding.LayEmployeeListItemBinding
import com.mykoatlincoadingsample.model.Employee

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.DataViewHolder>() {

    private var mEmployees: List<Employee>? = null

    /**
     * @desc method used to update the employee list
     */
    fun updateList(listData: List<Employee>?) {
        listData?.let {
            this.mEmployees = listData
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.lay_employee_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (mEmployees?.size != null) mEmployees!!.size else 0
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setViewModel(mEmployees?.get(position)!!)
    }

    override fun onViewAttachedToWindow(holder: DataViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: DataViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    override fun onFailedToRecycleView(holder: DataViewHolder): Boolean {
        holder.itemView.clearAnimation()
        return super.onFailedToRecycleView(holder)
    }


    /**
     * @desc this class is used for extend RecyclerView.ViewHolder and used by EmployeeListAdapter class
     */
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: LayEmployeeListItemBinding? = null

        init {
            bind()
        }

        /**
         * @desc method used to bind the item view
         */
        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
            }
        }

        /**
         * @desc method used fto unbind the item view
         */
        fun unbind() {
            binding?.unbind()
        }

        /**
         * @desc This method is used to set the binding with view Employee object
         */
        fun setViewModel(employee: Employee) {
            if (binding != null) {
                binding?.employee = employee
            }
        }
    }

}