package com.sunnyweater.setsteptime

import android.widget.Toast
import java.math.BigDecimal

object Utils {
    /**
     * double 四舍五入保留小数点后两位
     */
    fun  roundHalfUp(double:Double) :Float{

        return BigDecimal(double).setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()

    }
    fun showToast(msg: String){
        Toast.makeText(MyApplication.context, msg, Toast.LENGTH_SHORT).show()
    }
}