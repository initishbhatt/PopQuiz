package com.initishbhatt.popquiz.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import io.reactivex.disposables.Disposable

/**
 * @author nitishbhatt
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}