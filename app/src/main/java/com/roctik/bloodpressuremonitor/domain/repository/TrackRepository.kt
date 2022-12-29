package com.roctik.bloodpressuremonitor.domain.repository

import com.roctik.bloodpressuremonitor.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getAllTrack(): Flow<List<Track>>
    suspend fun addTrack(track: Track)
    suspend fun getTrackById(trackId: Long): Track
    suspend fun updateTrack(track: Track): Int
}