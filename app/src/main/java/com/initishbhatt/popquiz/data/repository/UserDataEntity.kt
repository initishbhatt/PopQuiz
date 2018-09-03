package com.initishbhatt.popquiz.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import com.initishbhatt.popquiz.presentation.profile.ProfileBindingModel
import io.reactivex.Single

/**
 * @author nitishbhatt
 */
@Entity(tableName = "User")
data class UserDataEntity(
        @PrimaryKey
        var userId: String,
        var userName: String,
        var userAge: String,
        var userGender: String,
        var userScore: Int? = 0
)

@Dao
interface UserDataDao {
    @Query("SELECT * FROM User")
    fun getAllUsers(): Single<List<UserDataEntity>>

    @Query("SELECT * FROM User WHERE userId=:userId")
    fun getCurrentUser(userId: String): Single<UserDataEntity>

    @Insert
    fun insertUserData(userDataEntity: UserDataEntity)

    @Query("UPDATE User SET userScore=:score WHERE userId=:userId")
    fun updateUserScore(score: Int,userId: String)
}

fun ProfileBindingModel.toUserEntity(userId: String) = UserDataEntity(userId = userId,
        userName = name,
        userAge = age,
        userGender = gender)