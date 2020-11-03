package com.example.myapplicationnextzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationnextzy.model.City
import com.example.myapplicationnextzy.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding viewModel with a view
        mainViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)
        // listener
        mainViewModel.cities.observe(this, Observer { citiesRetrieved(it) })
    }

    fun run(view: View){
        mainViewModel.retrieveCities()
    }

    fun changePage(view: View){
        val intent = Intent(this, CountdownTimerActivity::class.java)
        startActivity(intent)
    }

    fun citiesRetrieved(cities: List<City>){
        result.text = cities.joinToString("\n")
    }
}