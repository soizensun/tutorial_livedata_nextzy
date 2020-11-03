package com.example.myapplicationnextzy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationnextzy.viewModel.CountdownViewModel
import com.example.myapplicationnextzy.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_countdown_timer.*
import kotlin.math.log

class CountdownTimerActivity : AppCompatActivity() {
    private lateinit var viewModel : CountdownViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown_timer)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CountdownViewModel::class.java)


        viewModel.getSecond().observe(this, Observer {
            countDownTV.text = it.toString()
        })

        viewModel.timerValue.observe(this, Observer {
            countDownTV.text = viewModel.timerValue.value.toString()
        })

        viewModel.getFinished().observe(this, Observer {
            if(it)
                Toast.makeText(this, "time out", Toast.LENGTH_SHORT).show()
        })
    }

    fun startTimer(view: View){
        if(limitTV.text.isEmpty() || limitTV.text.length < 4)
            Toast.makeText(this, "Invalid time.", Toast.LENGTH_SHORT).show()
        else{
            viewModel.timerValue.value = limitTV.text.toString().toInt()
            viewModel.startTimer()
        }

    }

    fun stopTime(view: View){
        viewModel.stopTimer()
    }

    fun resetTime(view: View){
        viewModel.timerValue.value = 0
        Log.i("ffff", "resetTime: ${viewModel.timerValue.value.toString()}")
    }
}