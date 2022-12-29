package com.roctik.bloodpressuremonitor.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Track")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String,
    var description: String? = null,
    var date: Long,
    var systolic: Int,
    var diastolic: Int,
    var pulse: Int,
    @ColumnInfo(name = "created_at") var createdAt: Long = System.currentTimeMillis()
)