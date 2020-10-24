package com.example.whetherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whetherapp.data.repository.WhetherRepository

class MainViewModelFactory(
    private val whetherRepository: WhetherRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(whetherRepository) as T
            else -> throw IllegalArgumentException("Not found")
        }
    }
}