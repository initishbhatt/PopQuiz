package com.initishbhatt.popquiz.util

import android.databinding.BindingConversion
import android.view.View

/**
 * @author nitishbhatt
 */
@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) View.VISIBLE else View.GONE
}