package com.kk.tongfu.gasolineassistant.service

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by tongfu
 * on 2019-11-15
 * Desc:
 */

@Dao
interface DaoService {

    @Query("SELECT * FROM gasinfo ORDER BY date DESC")
    fun getGasInfos(): LiveData<List<GasRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg gasRecord: GasRecord)

    @Delete
    suspend fun delete(vararg gasRecords: GasRecord)

}