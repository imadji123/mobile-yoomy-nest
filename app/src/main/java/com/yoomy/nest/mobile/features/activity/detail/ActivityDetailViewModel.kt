package com.yoomy.nest.mobile.features.activity.detail

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(), DefaultLifecycleObserver {

    val activityId: String? = savedStateHandle["activityId"]

    init {
        Timber.d("Initializing ${this::class.simpleName} with activityId = $activityId")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Timber.d("Resuming ${this::class.simpleName}")
    }
}