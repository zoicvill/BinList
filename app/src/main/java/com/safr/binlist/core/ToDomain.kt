package com.safr.binlist.core

import com.safr.binlist.data.local.model.HistoryEntity
import com.safr.binlist.domain.model.HistoryItem

fun HistoryEntity.toDomain(): HistoryItem =
    HistoryItem(bin = bin, nameBank = nameBank)

fun HistoryItem.toEntity(): HistoryEntity =
    HistoryEntity(bin = bin?: "", nameBank = nameBank)