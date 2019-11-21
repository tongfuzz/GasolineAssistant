package com.kk.tongfu.gasolineassistant.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kk.tongfu.gasolineassistant.App
import com.kk.tongfu.gasolineassistant.R
import com.kk.tongfu.gasolineassistant.service.GasRecord
import java.text.SimpleDateFormat

/**
 * Created by tongfu
 * on 2019-11-19
 * Desc:
 */

@BindingAdapter("distance")
fun setDistance(view: TextView, text: Int) {
    view.text = "行驶总公里数：${text.toString()}"
}

@BindingAdapter("price")
fun setPrice(view: TextView, text: Double) {
    view.text = "价格：${text.toString()}"
}

@BindingAdapter("cost")
fun setCost(view: TextView, text: Double) {
    view.text = "金额：${text.toString()}"
}

@BindingAdapter("date")
fun setDate(view: TextView, text: Long) {
    view.text = SimpleDateFormat("yyyy-MM-dd").format(text)
}

@BindingAdapter("amount")
fun setAmount(view: TextView, text: Long) {
    view.text = "剩余公里数：${text.toString()}"
}

@BindingAdapter("gascost")
fun setGasCost(view: TextView, items: List<GasRecord>) {
    if (items.isNotEmpty()) {
        val currentItem = items[0]
        val preItem = items[1]
        if (preItem != null) {
            val distance =
                preItem.currentDistance + preItem.restAmount - currentItem.currentDistance - currentItem.restAmount

            val costPreKM = currentItem.cost / distance
            if (preItem.price > 0) {
                val gasCost = costPreKM * 100 / currentItem.price
                App.application?.resources?.getString(R.string.average_gas_mouth)?.apply {
                    view.visibility = View.VISIBLE
                    view.text = String.format(this, gasCost)
                }
            }
        } else {
            view.visibility = View.GONE
        }
    }
}

@BindingAdapter("price_prekm")
fun setPricePreKM(view: TextView, items: List<GasRecord>) {
    if (items.isNotEmpty()) {
        val currentItem = items[0]
        val preItem = items[1]
        if (preItem != null) {
            val distance =
                preItem.currentDistance + preItem.restAmount - currentItem.currentDistance - currentItem.restAmount

            val costPreKM = currentItem.cost / distance
            App.application?.resources?.getString(R.string.price_prekm_mouth)?.apply {
                view.visibility = View.VISIBLE
                view.text = String.format(this, costPreKM)
            }
        } else {
            view.visibility = View.GONE
        }
    }
}




