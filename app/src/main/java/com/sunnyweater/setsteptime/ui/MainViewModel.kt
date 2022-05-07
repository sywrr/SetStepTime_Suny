package com.sunnyweater.setsteptime.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweater.setsteptime.logic.Utils

class MainViewModel :ViewModel() {

    private val liveStepValue  = MutableLiveData<Double>()

    val liveData = Transformations.map(liveStepValue){
        value ->
        Utils.roundHalfUp(value)
    }
   fun setLiveData(stepTimeValue:Double){
       liveStepValue.postValue(stepTimeValue)
   }
}