package com.example.whetherapp.data.repository

import com.example.whetherapp.base.ApiResult
import com.example.whetherapp.data.model.Whether

interface WhetherRepository {

    suspend fun getWhether(cityName: String): ApiResult<Whether>
}