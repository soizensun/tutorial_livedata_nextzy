package com.example.myapplicationnextzy.model

class City constructor(
    private val cityName: String,
    private val countryName:String
) {
    override fun toString(): String {
        return "$cityName - $countryName"
    }
}