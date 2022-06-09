package com.example.mypizza.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mypizza.data.dao.CustomerInfoDao
import com.example.mypizza.data.dao.StoreInformationDao
import com.example.mypizza.data.dao.StoreItemsDao
import com.example.mypizza.data.entities.CustomerInfo
import com.example.mypizza.data.entities.StoreInformation
import com.example.mypizza.data.entities.StoreItems
import com.example.mypizza.data.room.MyPizzaDatabase.Companion.DB_VERSION

@Database(entities = arrayOf(StoreItems::class,StoreInformation::class, CustomerInfo::class), version = DB_VERSION, exportSchema = false)
abstract class MyPizzaDatabase : RoomDatabase(){

    abstract fun storeInfoDao(): StoreInformationDao

    abstract fun storeItemsDao(): StoreItemsDao

    abstract fun customerInfoDao(): CustomerInfoDao

    companion object {
        const val DB_VERSION = 4
        private const val DB_NAME = "MyPizzaDatabase_Database"

        fun initDatabase(context: Context): MyPizzaDatabase {
            return Room.databaseBuilder(
                context,
                MyPizzaDatabase::class.java, DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}