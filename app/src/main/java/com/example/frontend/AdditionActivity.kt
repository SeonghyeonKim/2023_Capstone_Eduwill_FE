package com.example.frontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.frontend.databinding.ActivityAdditionBinding

class AdditionActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAdditionBinding

    companion object {
        var requestQueue: RequestQueue?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)

        initHttpRequest()

        setContentView(R.layout.activity_addition)
    }


    fun initHttpRequest() {
        MainActivity.requestQueue = Volley.newRequestQueue(this)
    }


}