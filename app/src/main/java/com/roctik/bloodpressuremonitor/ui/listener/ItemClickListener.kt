package com.roctik.bloodpressuremonitor.ui.listener

import com.roctik.bloodpressuremonitor.domain.model.ItemAction

interface ItemClickListener<in T> {
    fun onItemClick(item: T, action: ItemAction)
}