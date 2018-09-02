package com.initishbhatt.popquiz.data.store

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author nitishbhatt
 */
class PrefStore constructor( val sharedPreferences: SharedPreferences) {
    fun setUserId(userId: String) = sharedPreferences.edit()
            .putString(PrefKey.USER_ID, userId)
            .apply()

    fun getCurrentUserId() = sharedPreferences.getString(PrefKey.USER_ID, "")

    fun setDeleteUserId(userId: String) = sharedPreferences.edit()
            .remove(PrefKey.USER_ID)
            .apply()
}

object PrefKey {
    const val USER_ID = "userID"
}