package com.example.mypizza.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.mypizza.data.dao.CustomerInfoDao
import com.example.mypizza.data.entities.CustomerInfo
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CustomerRepository @Inject constructor(val dao: CustomerInfoDao) {

    fun insertUser(user: CustomerInfo): Single<Long> {
        return dao.insertUser(user)
    }

    fun loginUser(user: CustomerInfo): LiveData<CustomerInfo> {
        return dao.loginUser(user.username, user.password).asLiveData()
    }

}