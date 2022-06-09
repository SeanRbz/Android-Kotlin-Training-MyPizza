package com.example.mypizza.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypizza.data.entities.CustomerInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow


@Dao
interface CustomerInfoDao {

    @Query("SELECT * FROM CustomerInfo WHERE :username == CustomerInfo.username AND :password == CustomerInfo.password ")
    fun loginUser(username:String, password:String): Flow<CustomerInfo>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: CustomerInfo):Single<Long>

}