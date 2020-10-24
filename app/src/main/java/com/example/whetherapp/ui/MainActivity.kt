package com.example.whetherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.whetherapp.R
import com.example.whetherapp.base.ApiResult
import com.example.whetherapp.base.InjectorObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels {
        InjectorObject.getMainViewModelFactory()
    }

    private var searchJob: Job? = null

    fun request(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        et_search_bar.addTextChangedListener {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(1000)
                request(it.toString())
            }
        }

        mainViewModel.whether.observe(this) {
            val text = when (it) {
                is ApiResult.Success -> getString(R.string.int_c, it.data.main.temp.toInt())
                is ApiResult.Error -> it.throwable.message.toString()
                is ApiResult.Loading -> "Loading..."
            }
            tv_whether.text = text
        }
    }
}