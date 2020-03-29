package com.wcynthia.calculationtest.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import java.util.*
import java.util.logging.Level

class MyViewModel(application: Application, savedStateHandle: SavedStateHandle): AndroidViewModel(application) {
    companion object{
        const val KEY_HIGH_SCORE = "key_high_score"
        const val KEY_LEFT_NUMBER = "key_left_number"
        const val KEY_RIGHT_NUMBER = "key_right_number"
        const val KEY_OPERATOR = "key_operator"
        const val KEY_ANSWER = "key_answer"
        const val KEY_CURRENT_SCORE = "key_current_score"
        const val SAVE_SHP_DATA = "save_shp_data_name"
    }
    private var win_flag = false
    private val handle: SavedStateHandle

    init {
        if (!savedStateHandle.contains(KEY_HIGH_SCORE)){
            val shp = getApplication<Application>().getSharedPreferences(SAVE_SHP_DATA, Context.MODE_PRIVATE)
            savedStateHandle.set(KEY_HIGH_SCORE,shp.getInt(KEY_HIGH_SCORE,0))
            savedStateHandle.set(KEY_LEFT_NUMBER,0)
            savedStateHandle.set(KEY_RIGHT_NUMBER,0)
            savedStateHandle.set(KEY_OPERATOR,"+")
            savedStateHandle.set(KEY_ANSWER,0)
            savedStateHandle.set(KEY_CURRENT_SCORE,0)
        }
        this.handle = savedStateHandle
    }
    fun getLeftNumber():MutableLiveData<Int>{
        return handle.getLiveData(KEY_LEFT_NUMBER)
    }
    fun getRightNumber():MutableLiveData<Int>{
        return handle.getLiveData(KEY_RIGHT_NUMBER)
    }
    fun getOperator():MutableLiveData<String>{
        return handle.getLiveData(KEY_OPERATOR)
    }
    fun getHighScore():MutableLiveData<Int>{
        return handle.getLiveData(KEY_HIGH_SCORE)
    }
    fun getCurrentScore():MutableLiveData<Int>{
        return handle.getLiveData(KEY_CURRENT_SCORE)
    }
    fun getAnswer():MutableLiveData<Int>{
        return handle.getLiveData(KEY_ANSWER)
    }
    fun generator(){
        val level = 20
        val random = Random()
        val x = random.nextInt(level)+1
        val y = random.nextInt(level)+1
        if (x%2==0){
            getOperator().value = "+"
            getRightNumber().value = x
            getLeftNumber().value = y
            getAnswer().value = x+y
        }else{
            getOperator().value = "-"
            if (x>y){
                getRightNumber().value = x
                getLeftNumber().value = y
                getAnswer().value = x-y
            }else{
                getRightNumber().value = y
                getLeftNumber().value = x
                getAnswer().value = y-x
            }
        }
    }
    fun save(){
        val shp = getApplication<Application>().getSharedPreferences(SAVE_SHP_DATA,Context.MODE_PRIVATE)
        val editor = shp.edit()
        editor.putInt(KEY_HIGH_SCORE, getHighScore().value!!)
        editor.apply()
    }
    fun answerCorrect(){
        getCurrentScore().value = getCurrentScore().value!!+1
        if(getCurrentScore().value!!>getHighScore().value!!){
            getHighScore().value = getCurrentScore().value
        }
        win_flag = true
        generator()
    }
}