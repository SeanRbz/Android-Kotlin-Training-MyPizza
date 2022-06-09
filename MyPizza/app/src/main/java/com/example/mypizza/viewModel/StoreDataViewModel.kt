package com.example.mypizza.viewModel

import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mypizza.data.entities.CustomerInfo
import com.example.mypizza.data.entities.StoreInformation
import com.example.mypizza.data.entities.StoreItems
import com.example.mypizza.data.repository.CustomerRepository
import com.example.mypizza.data.repository.StoreDataRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.ScheduledDirectTask
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.subscribeOn
import javax.inject.Inject

class StoreDataViewModel @ViewModelInject constructor(private val repo: StoreDataRepository): ViewModel(){

    val storeInfo  = repo.storeInfo

    val storeItems = repo.storeItems

    val updateSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val addItemsSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun updateStoreInfo(data: StoreInformation){
        repo.updateStoreInfo(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newRowId: Int?, throwable: Throwable? ->
                updateSuccess.value = throwable == null
            }
    }


    fun insertStoreInfo(data: StoreInformation){
        repo.insertStoreInfo(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }


    fun addItem(data: StoreItems){
        repo.insertItem(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())  .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newRowId: Long?, throwable: Throwable? ->
                addItemsSuccess.value = throwable == null
            }
    }


    companion object{
        val DEFAULT_STORE_INFO = StoreInformation(id = 1,name = "Unamed Store",address = "Somewhere in the world")
    }
}