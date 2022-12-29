package com.roctik.bloodpressuremonitor.util

import android.annotation.SuppressLint
import com.roctik.bloodpressuremonitor.util.Constants.DATE_TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun dateTimeFromString(source: Long): String {
    val sdf = SimpleDateFormat(DATE_TIME_FORMAT)
    val netDate = Date(source)

    return sdf.format(netDate)
}