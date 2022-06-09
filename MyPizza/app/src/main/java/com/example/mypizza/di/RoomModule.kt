package com.example.mypizza.di

import android.content.Context
import com.example.mypizza.data.repository.CustomerRepository
import com.example.mypizza.data.repository.StoreDataRepository
import com.example.mypizza.data.room.MyPizzaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): MyPizzaDatabase =
        MyPizzaDatabase.initDatabase(context)

    @Singleton
    @Provides
    fun providesStoreDataRepository(db: MyPizzaDatabase): StoreDataRepository =
        StoreDataRepository(db.storeInfoDao(),db.storeItemsDao())

    @Singleton
    @Provides
    fun providesCustomerRepository(db: MyPizzaDatabase): CustomerRepository =
        CustomerRepository(db.customerInfoDao())

}