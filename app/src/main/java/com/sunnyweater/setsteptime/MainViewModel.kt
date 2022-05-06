package com.sunnyweater.setsteptime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel() {

    private val liveSetpValue  = MutableLiveData<Double>()

    val liveData = Transformations.map(liveSetpValue){
        value -> Utils.roundHalfUp(value)
    }
   fun setLiveData(stepTimeValue:Double){
       liveSetpValue.postValue(stepTimeValue)
   }
}