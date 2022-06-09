package com.example.mypizza

import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Core: MultiDexApplication(), LifecycleObserver {
}