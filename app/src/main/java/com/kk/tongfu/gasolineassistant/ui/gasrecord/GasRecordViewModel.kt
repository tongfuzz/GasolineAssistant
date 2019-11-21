package com.kk.tongfu.gasolineassistant.ui.gasrecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kk.tongfu.gasolineassistant.App
import com.kk.tongfu.gasolineassistant.service.GasRecord
import kotlinx.coroutines.launch

class GasRecordViewModel : ViewModel() {


    val gasInfos = App.dataBase?.daoService()?.getGasInfos()

    fun delete(item: GasRecord) {
        viewModelScope.launch {
            App.dataBase?.daoService()?.delete(item)
        }
    }
}