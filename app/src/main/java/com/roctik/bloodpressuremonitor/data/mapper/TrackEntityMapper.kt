package com.roctik.bloodpressuremonitor.data.mapper

import com.roctik.bloodpressuremonitor.data.source.local.entity.TrackEntity
import com.roctik.bloodpressuremonitor.domain.model.Track

fun TrackEntity.toModel() = Track(
    id, title, description, date, systolic, diastolic, pulse, createdAt
)

fun List<TrackEntity>.toModel(): List<Track> {
    return map {
        it.toModel()
    }
}

fun Track.toEntity() = TrackEntity(
    id, title, description, date, systolic, diastolic, pulse, createdAt
)
