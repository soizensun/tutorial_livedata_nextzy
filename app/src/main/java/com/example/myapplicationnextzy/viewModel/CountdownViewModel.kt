package com.example.myapplicationnextzy.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CountdownViewModel : ViewModel(){

    private lateinit var timer : CountDownTimer
    private val second = MutableLiveData<Int>()
    private var finished = MutableLiveData<Boolean>()
    var timerValue = MutableLiveData<Int>()

    fun getSecond(): LiveData<Int> {
        return second
    }

    fun getFinished(): LiveData<Boolean>{
        return finished
    }

    fun startTimer(){
        timer = object : CountDownTimer(timerValue.value.toString().toLong(), 1000){
            override fun onFinish() {
                finished.value = true
            }
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                second.value = timeLeft.toInt()
            }

        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }
}