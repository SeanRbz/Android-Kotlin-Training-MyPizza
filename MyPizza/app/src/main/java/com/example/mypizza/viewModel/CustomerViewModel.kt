package com.example.mypizza.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypizza.data.entities.CustomerInfo
import com.example.mypizza.data.repository.CustomerRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CustomerViewModel @ViewModelInject constructor(private val repo: CustomerRepository) :
    ViewModel() {

    var isValidForRegistration: Boolean = false

    var loginPageResponse: MutableLiveData<Pair<Int,String>?> = MutableLiveData(null)

    fun validateUserFields(username: String, password: String): Single<Boolean> {

        val userIsValid = username.isNotEmpty() || username.isNotBlank()

        val passwordIsValid = password.isNotEmpty() || password.isNotBlank()

        return Single.create<Boolean> { emitter ->
            if (userIsValid && passwordIsValid) {
                isValidForRegistration = true
                emitter.onSuccess(isValidForRegistration)
            } else {
                isValidForRegistration = false
                emitter.onSuccess(isValidForRegistration)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun register(username: String, password: String) {
        val user = CustomerInfo(username = username, password = password,id = null)
        repo.insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newRowId: Long?, throwable: Throwable? ->
                if (throwable != null) {
                    loginPageResponse.value = Pair(0,throwable.localizedMessage)
                } else {
                    loginPageResponse.value = Pair(newRowId.toString().toIntOrNull() ?: 0,"")
                }

            }
    }

    fun loginUser(username: String, password: String): LiveData<CustomerInfo> {
        val user = CustomerInfo(username = username, password = password,id = null)
        return repo.loginUser(user)
    }
}