package com.example.basketballscoring

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle

//当程序异常退出时，savedstatehandle里的数据会存活
class MyViewModel(application: Application, private val handle: SavedStateHandle) :
    AndroidViewModel(application) {
    //    private var aTeamScore = MutableLiveData<Int>()
    private var bTeamScore = MutableLiveData<Int>()
    private var aTeamBack = 0
    private var bTeamBack = 0
    private val key = application.resources.getString(R.string.data_key)
    private val shpName = application.resources.getString(R.string.shp_name)

    private fun load() {
        val shp = getApplication<Application>().getSharedPreferences(shpName, Context.MODE_PRIVATE)
        handle.set(key, shp.getInt(key, 0))
    }

    fun save() {
        val shp = getApplication<Application>().getSharedPreferences(shpName, Context.MODE_PRIVATE)
        val editor = shp.edit()
        editor.putInt(key, handle.getLiveData<Int>(key).value!!).apply()
    }

    private fun setBackValue() {
        aTeamBack = getATeamScore().value!!
        bTeamBack = bTeamScore.value!!
    }

    fun getATeamScore(): MutableLiveData<Int> {
        if (!handle.contains(key)) {
            load()
        }
//        if (aTeamScore.value == null){
//            Log.e("print","aTeamScore.value == null")
//            aTeamScore.value = 0
//        }
        return handle.getLiveData(key)
    }

    fun getBTeamScore(): MutableLiveData<Int> {
        if (bTeamScore.value == null) {
            Log.e("print", "bTeamScore.value == null")
            bTeamScore.value = 0
        }
        return bTeamScore
    }

    fun addATeam(plusScore: Int) {
        setBackValue()
        getATeamScore().value = getATeamScore().value!! + plusScore
        //save()
    }

    fun addBTeam(plusScore: Int) {
        setBackValue()
        bTeamScore.value = bTeamScore.value!! + plusScore
    }

    fun reset() {
        setBackValue()
        getATeamScore().value = 0
        bTeamScore.value = 0
    }

    fun undo() {
        getATeamScore().value = aTeamBack
        bTeamScore.value = bTeamBack
    }
}