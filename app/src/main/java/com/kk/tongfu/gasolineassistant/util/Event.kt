package com.kk.tongfu.gasolineassistant.util

import androidx.lifecycle.Observer

/**
 * Created by tongfu
 * on 2019-11-19
 * Desc:
 */

open class Event<out T> (private val content:T){
    var hasBeenHandled=false
    private set

    fun getContentIfNotHandled():T?{
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled=true
            content
        }
    }

    fun peekContent():T=content
}

class EventObserver<T>(private val onEventUnhandledContent:(T)->Unit):Observer<Event<T>>{
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            value->onEventUnhandledContent(value)
        }
    }

}