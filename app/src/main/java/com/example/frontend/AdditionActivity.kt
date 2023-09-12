package com.example.frontend

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
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
        binding.addUpdateBtn.setOnClickListener {
            Toast.makeText(this, "테스트", Toast.LENGTH_SHORT).show()
            httpRequestUpload()
        }

        setContentView(binding.root)
    }

    fun initHttpRequest() {
        requestQueue = Volley.newRequestQueue(this)
    }

    fun httpRequestUpload() {
        val request = object: StringRequest(
            Request.Method.POST,
            "http://172.22.54.97:3389/upload",
            Response.Listener<String> {
                finish()
            },
            Response.ErrorListener {
                Toast.makeText(this, "업로드에 실패하였습니다.", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["userId"] = "10"
                params["title"] = binding.addName.text.toString()
                params["content"] = binding.addInform.text.toString()
                params["price"] = binding.addPrice.text.toString()
                params["numberOfImages"] = "0"
                params["video"] = ""
                params["image_1"] = ""
                params["image_2"] = ""
                params["image_3"] = ""
                params["image_4"] = ""
                params["image_5"] = ""
                params["xSize"] = binding.addXSize.text.toString()
                params["ySize"] = binding.addYSize.text.toString()
                params["zSize"] = binding.addZSize.text.toString()

                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Content-Type","multipart/form-data")
                return headers
            }
        }
        request.setShouldCache(false)
        requestQueue?.add(request)
    }
}