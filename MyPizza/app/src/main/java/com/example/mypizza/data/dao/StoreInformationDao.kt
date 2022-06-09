package com.example.mypizza.data.dao


import androidx.room.*
import com.example.mypizza.data.entities.StoreInformation
import com.example.mypizza.data.entities.StoreItems
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow


@Dao
interface StoreInformationDao  {
    @Query("SELECT * FROM StoreInformation WHERE id =:id LIMIT 1")
    fun loadStoreinfo(id:Int = 1): Flow<StoreInformation>


    @Update
    fun updateStoreInfo(vararg store: StoreInformation):Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStoreInfo(user: StoreInformation): Single<Long>
}