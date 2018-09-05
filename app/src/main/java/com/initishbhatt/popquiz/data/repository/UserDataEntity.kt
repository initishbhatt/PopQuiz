package com.initishbhatt.popquiz.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import com.initishbhatt.popquiz.view.binding.ProfileBindingModel
import io.reactivex.Single

/**
 * @author nitishbhatt
 */
@Entity(tableName = "User")
data class UserDataEntity(
        @PrimaryKey(autoGenerate = true)
        var userId: Int = 0,
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
    fun updateUserScore(score: Int, userId: Int)
}

fun ProfileBindingModel.toUserEntity() = UserDataEntity(
        userName = name,
        userAge = age,
        userGender = gender)