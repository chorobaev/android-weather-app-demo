package com.example.whetherapp.data.repository

import com.example.whetherapp.base.ApiResult
import com.example.whetherapp.base.apiCall
import com.example.whetherapp.data.model.Whether
import com.example.whetherapp.data.remote.WhetherClient

class WhetherRepositoryImpl(
    private val whetherClient: WhetherClient
) : WhetherRepository {


    override suspend fun getWhether(cityName: String): ApiResult<Whether> {
        return apiCall { whetherClient.getWhether(cityName) }
    }

    fun just() {

    }
}