package com.kk.tongfu.gasolineassistant

import android.app.Application
import androidx.room.Room
import com.kk.tongfu.gasolineassistant.service.AppDataBase

/**
 * Created by tongfu
 * on 2019-11-15
 * Desc:
 */

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        application=this
        dataBase = Room.databaseBuilder(this, AppDataBase::class.java, "app_data").build()
    }

    companion object {
        @JvmField
        var application: Application? = null

        @JvmField
        var dataBase: AppDataBase? = null
    }

}