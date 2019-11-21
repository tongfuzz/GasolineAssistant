package com.kk.tongfu.gasolineassistant.ui.gasaverage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kk.tongfu.gasolineassistant.App
import com.kk.tongfu.gasolineassistant.service.GasRecord
import com.kk.tongfu.gasolineassistant.util.Event
import kotlinx.coroutines.launch

/**
 * Created by tongfu
 * on 2019-11-15
 * Desc:
 */

class AddGasRecordViewModel : ViewModel() {

    private val _navigationToRecordAction = MutableLiveData<Event<Int>>()
    val navigationToRecordAction: MutableLiveData<Event<Int>>
        get() = _navigationToRecordAction

    fun addGasRecord(
        id:Int,
        date: Long,
        price: Double,
        cost: Double,
        amount: Int,
        distance: Int
    ) {
        val gasRecord = GasRecord(id, date, price, cost, amount, distance)
        viewModelScope.launch {
            App.dataBase?.daoService()?.insert(gasRecord)
            _navigationToRecordAction.value=Event(0)
        }
    }
}