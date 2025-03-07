package com.ferhat.jetpackcomposeflyticketapp.ViewModel

import androidx.lifecycle.LiveData
import com.ferhat.jetpackcomposeflyticketapp.Domain.FlightModel
import com.ferhat.jetpackcomposeflyticketapp.Domain.LocationModel
import com.ferhat.jetpackcomposeflyticketapp.Repository.MainRepository

class MainViewModel() {

    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>> {
        return repository.loadLocation()
    }

    fun loadFiltered(from: String, to: String):
            LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)
    }
}