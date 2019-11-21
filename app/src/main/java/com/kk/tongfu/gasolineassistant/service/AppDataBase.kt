package com.kk.tongfu.gasolineassistant.service

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by tongfu
 * on 2019-11-15
 * Desc:
 */

@Database(entities = arrayOf(GasRecord::class),version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun daoService():DaoService
}