package com.kk.tongfu.gasolineassistant.service

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by tongfu
 * on 2019-11-15
 * Desc:加油信息
 */
@Entity(tableName = "gasinfo")
@Parcelize
data class GasRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "cost") val cost: Double,
    @ColumnInfo(name = "rest_amount") val restAmount: Int,
    @ColumnInfo(name = "distance") val currentDistance: Int
):Parcelable