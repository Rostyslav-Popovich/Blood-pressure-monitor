package com.roctik.bloodpressuremonitor.data.repository

import com.roctik.bloodpressuremonitor.data.mapper.toEntity
import com.roctik.bloodpressuremonitor.data.mapper.toModel
import com.roctik.bloodpressuremonitor.data.source.local.AppDatabase
import com.roctik.bloodpressuremonitor.domain.model.Track
import com.roctik.bloodpressuremonitor.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrackRepositoryImpl(private val database: AppDatabase) : TrackRepository {
    override fun getAllTrack(): Flow<List<Track>> {
        return database.trackDao.loadAllTrack().map { it.toModel() }
    }

    override suspend fun addTrack(track: Track) {
        database.trackDao.insertTrack(track.toEntity())
    }

    override suspend fun getTrackById(trackId: Long): Track {
        return database.trackDao.getTrackByTaskId(trackId).toModel()
    }

    override suspend fun updateTrack(track: Track): Int {
        return database.trackDao.updateTrack(track.toEntity())
    }

}