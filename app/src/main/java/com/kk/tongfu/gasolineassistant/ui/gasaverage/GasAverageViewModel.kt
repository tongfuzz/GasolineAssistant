package com.kk.tongfu.gasolineassistant.ui.gasaverage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kk.tongfu.gasolineassistant.App

class GasAverageViewModel : ViewModel() {

    val gasInfos = App.dataBase?.daoService()?.getGasInfos()

    var totalCost = MutableLiveData<Double>(0.00)
    var totalDistance = MutableLiveData<Int>(0)
    var averageGas = MutableLiveData<Double>(0.00)
    var averagePrice = MutableLiveData<Double>(0.00)
    var averageCost = MutableLiveData<Double>(0.00)
    var pricePreKM = MutableLiveData<Double>(0.00)

    fun calculate() {
        gasInfos?.value?.let {
            if (!it.isNullOrEmpty()) {
                if (it.size >= 2) {
                    val listWithPrice = it.filter { gasRecord -> gasRecord.price > 0 }
                    //平均油品价格
                    val averageprice = listWithPrice.sumByDouble { gasRecord ->
                        gasRecord.price
                    } / listWithPrice.size

                    averagePrice.value = averageprice

                    //多于两个
                    /* var totalCosts=0.00
                     for (i in it.size-1 until 1){
                         //总的花费取最新一次油耗记录之前的所有花费
                         totalCosts+=it[i].cost

                         *//*val preRecord = it[i]
                        val currentRecord = it[i-1]
                        //距离
                        val distance =
                            currentRecord.currentDistance + currentRecord.restAmount - preRecord.currentDistance - preRecord.restAmount
                        pricePreKM=preRecord.cost/distance*//*

                    }*/

                    //所有记录有价格的油耗记录

                    val first = it.first()

                    val totalCosts = it.sumByDouble { item ->
                        item.cost
                    } - first.cost
                    totalCost.value = totalCosts

                    val last = it.last()

                    val totalKM =
                        first.currentDistance + first.restAmount - last.currentDistance - last.restAmount

                    pricePreKM.value = totalCosts / totalKM
                    averageGas.value = totalCosts / totalKM * 100 / averageprice
                    //平均花费
                    averageCost.value = totalCosts / (it.size - 1)
                } else {
                    //一个
                    totalCost.value = 0.00
                    totalDistance.value = 0
                    averageGas.value = 0.00
                    averagePrice.value = 0.00
                    averageCost.value = 0.00
                    pricePreKM.value = 0.00
                }
                //总距离取最新添加的一次油耗记录时当前里程
                totalDistance.value = it.first().currentDistance
            }
        }
    }

}