package com.roctik.bloodpressuremonitor.data.source.local.dao

import androidx.room.*
import com.roctik.bloodpressuremonitor.data.source.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(trackEntity: TrackEntity): Long

    @Query("SELECT * FROM Track")
    fun loadAllTrack(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM Track WHERE id = :trackId")
    fun getTrackByTaskId(trackId: Long): TrackEntity

    @Update
    suspend fun updateTrack(trackEntity: TrackEntity): Int
}