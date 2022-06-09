package com.example.mypizza.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypizza.data.entities.StoreItems
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow


@Dao
interface StoreItemsDao {
    @Query("SELECT * FROM StoreItems")
    fun loadAll(): Flow<List<StoreItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStoreItem(user: StoreItems): Single<Long>
}