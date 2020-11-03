package com.example.myapplicationnextzy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationnextzy.model.City
import kotlinx.coroutines.*

class MainViewModel : ViewModel(){
    var cities = MutableLiveData<List<City>>()

    private val viewModelJob = SupervisorJob()
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun retrieveCities(){
        mainScope.launch(Dispatchers.IO){
            delay(1000)
            val list = listOf(
                City(
                    "Bangkok",
                    "Thailand"
                ),
                City("BuiJing", "China")
            )

            withContext(Dispatchers.Main){
                cities.value = list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}