package com.example.whetherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.whetherapp.base.ApiResult
import com.example.whetherapp.data.model.Whether
import com.example.whetherapp.data.repository.WhetherRepository

class MainViewModel(
    private val whetherRepository: WhetherRepository
) : ViewModel() {

    init {
    }

    val whether = liveData<ApiResult<Whether>> {
        emit(ApiResult.Loading)
        val result = whetherRepository.getWhether("Bishkek")
        emit(result)
    }
}