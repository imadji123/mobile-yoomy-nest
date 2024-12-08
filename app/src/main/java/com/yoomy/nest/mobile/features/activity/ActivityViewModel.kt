package com.yoomy.nest.mobile.features.activity

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : ViewModel(), DefaultLifecycleObserver {

    init {
        Timber.d("Initializing ${this::class.simpleName}")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Timber.d("Resuming ${this::class.simpleName}")
    }
}