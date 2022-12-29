package com.roctik.bloodpressuremonitor.ui.track

import androidx.lifecycle.MutableLiveData
import com.roctik.bloodpressuremonitor.domain.model.Track
import com.roctik.bloodpressuremonitor.domain.usecase.TrackUseCase
import com.roctik.bloodpressuremonitor.ui.base.BaseViewModel

class TrackViewModel(private val trackUseCase: TrackUseCase) : BaseViewModel() {

    val trackListLiveData = MutableLiveData<List<Track>>()

    init {
        getTrack()
    }

    private fun getTrack() = launch {
        trackUseCase.getTrackList().collect {
            trackListLiveData.postValue(it)
        }
    }

    fun addTrack(track: Track) = launch {
        trackUseCase.addTrack(track)
    }

    fun updateTrack(track: Track) = launch {
        trackUseCase.updateTrack(track)
    }

}