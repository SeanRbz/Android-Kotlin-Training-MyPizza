package com.example.mypizza.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.mypizza.data.dao.StoreInformationDao
import com.example.mypizza.data.dao.StoreItemsDao
import com.example.mypizza.data.entities.CustomerInfo
import com.example.mypizza.data.entities.StoreInformation
import com.example.mypizza.data.entities.StoreItems
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class StoreDataRepository @Inject constructor(
    private val daoStoreInfo: StoreInformationDao,
    private val daoStoreItems: StoreItemsDao
) {

    val storeInfo: LiveData<StoreInformation> = daoStoreInfo.loadStoreinfo().asLiveData()

    val storeItems: LiveData<List<StoreItems>> = daoStoreItems.loadAll().asLiveData()

    fun updateStoreInfo(data: StoreInformation): Single<Int> {
       return daoStoreInfo.updateStoreInfo(data)
    }


    fun insertStoreInfo(user: StoreInformation): Single<Long> {
        return  daoStoreInfo.insertStoreInfo(user)
    }

    fun insertItem(data: StoreItems): Single<Long> {
        return daoStoreItems.addStoreItem(data)
    }

}