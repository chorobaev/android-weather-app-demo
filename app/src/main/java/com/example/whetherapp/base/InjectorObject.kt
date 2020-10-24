package com.example.whetherapp.base

import com.example.whetherapp.data.remote.WhetherClient
import com.example.whetherapp.data.repository.WhetherRepository
import com.example.whetherapp.data.repository.WhetherRepositoryImpl
import com.example.whetherapp.ui.MainViewModelFactory

object InjectorObject {

    val whetherClient = WhetherClient()

    val whetherRepository: WhetherRepository = WhetherRepositoryImpl(whetherClient)

    fun getMainViewModelFactory() = MainViewModelFactory(whetherRepository)
}