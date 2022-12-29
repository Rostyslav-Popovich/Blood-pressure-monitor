package com.roctik.bloodpressuremonitor.domain.usecase

import com.roctik.bloodpressuremonitor.domain.model.Track
import com.roctik.bloodpressuremonitor.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class TrackUseCase(private val trackRepository: TrackRepository) {

    fun getTrackList(): Flow<List<Track>> {
        return trackRepository.getAllTrack()
    }

    suspend fun addTrack(track: Track) {
        trackRepository.addTrack(track)
    }

    suspend fun updateTrack(track: Track) {
        trackRepository.updateTrack(track)
    }
}