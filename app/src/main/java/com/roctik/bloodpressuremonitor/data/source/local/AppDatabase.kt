package com.roctik.bloodpressuremonitor.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roctik.bloodpressuremonitor.data.source.local.dao.TrackDao
import com.roctik.bloodpressuremonitor.data.source.local.entity.TrackEntity

@Database(
    entities = [TrackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val trackDao: TrackDao
}