package com.roctik.bloodpressuremonitor.domain.model

data class Track(
    var id: Long = 0,
    var title: String,
    var description: String?,
    var date: Long,
    var systolic: Int,
    var diastolic: Int,
    var pulse: Int,
    var createdAt: Long = System.currentTimeMillis()
)