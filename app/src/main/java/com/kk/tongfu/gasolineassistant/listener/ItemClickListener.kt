package com.kk.tongfu.gasolineassistant.listener

import android.view.View

/**
 * Created by tongfu
 * on 2019-11-20
 * Desc:
 */

interface ItemClickListener<T> {

    fun onClick(view: View, item: T)

}