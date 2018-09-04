package com.initishbhatt.popquiz.util

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.inputmethod.InputMethodManager
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment
import java.util.Random

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

/**
 * Extension function to get the range of random numbers
 */
fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) + start

/**
 * extension function for fragment transactions
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun DaggerAppCompatActivity.replaceFragment(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
    }
}

fun DaggerFragment.replaceFragment(fragment: Fragment, @IdRes frameId: Int) {
    fragmentManager?.inTransaction {
        replace(frameId, fragment)
    }
}
