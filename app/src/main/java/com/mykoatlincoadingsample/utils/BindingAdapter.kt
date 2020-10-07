package com.mykoatlincoadingsample.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @param view and boolean isMultipleGrid
 * @description This method is used to set layout manager on the view.
 */
@BindingAdapter("android:show_divider")
fun setShowDivider(view: RecyclerView, isShow: Boolean) {
    if (isShow) {
        view.addItemDecoration(DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL))
    }
}
