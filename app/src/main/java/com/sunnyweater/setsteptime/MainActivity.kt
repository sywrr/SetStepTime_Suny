package com.sunnyweater.setsteptime

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sunnyweater.setsteptime.databinding.ActivityMainBinding
import com.sunnyweater.setsteptime.ui.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity(), View.OnTouchListener,View.OnLongClickListener {
    private lateinit var binding: ActivityMainBinding;
    private var defaultValue: Double = 4.40
    private var mStepTimeValue = 4.40f
    private var mBauValue: Int = 115200
    private lateinit var timer: Timer
    val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBauView()
        initView()

        viewModel.liveData.observe(this, { value ->
            binding.bauText.text = value
                .toString()
        })
    }

    private fun initBauView() {
        binding.BauSpinner.apply {
            setItems(listOf("9600", "14400", "19200", "38400", "115200", "128000"))
            setOnItemSelectedListener { _, _, _, item ->
                when (item) {
                    "9600" -> mBauValue = 9600
                    "14400" -> mBauValue = 14400
                    "19200" -> mBauValue = 19200
                    "38400" -> mBauValue = 38400
                    "115200" -> mBauValue = 115200
                    "128000" -> mBauValue = 128000
                }
            }
        }
    }

    private fun initView() {

        binding.bauText.text = mStepTimeValue.toString()
        binding.btnIncrease.setOnTouchListener(this)
        binding.btnDecrease.setOnTouchListener(this)
        binding.btnDecrease.setOnLongClickListener(this)
        binding.btnDecrease.setOnClickListener {

            defaultValue -= 0.01
            viewModel.setLiveData(defaultValue)
        }
        binding.btnIncrease.setOnClickListener {
            defaultValue += 0.01
            viewModel.setLiveData(defaultValue)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        when (v) {

            binding.btnIncrease -> {
                when (event?.action) {
                    MotionEvent.ACTION_BUTTON_PRESS -> {
                        timer = Timer(true)
                        timer.schedule(object :TimerTask(){
                            override fun run() {
                                defaultValue +=0.01
                                viewModel.setLiveData(defaultValue)
                            }
                        }, 1000, 200)
                    }
                    MotionEvent.ACTION_UP -> {
                        timer.cancel()
                        timer.purge()
                    }
                }
            }
            binding.btnDecrease -> {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        println("enter")
                        timer = Timer(true)
                        timer.schedule(object : TimerTask() {
                            override fun run() {
                                defaultValue -= 0.01
                                viewModel.setLiveData(defaultValue)
                            }
                        }, 1000, 200)
                    }
                    MotionEvent.ACTION_UP -> {
                        timer.cancel();
                        timer.purge();
                    }
                }
            }
        }
        return false
    }

    override fun onLongClick(v: View?): Boolean {
//        TODO("Not yet implemented")
        when(v){
            binding.btnDecrease -> {
//                println("enter")
            }
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
       if (keyCode ==KeyEvent.ACTION_DOWN){
           println("enter")
       }
        return false
    }
}