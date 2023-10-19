package com.example.frontend

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.NetworkResponse
import com.android.volley.NoConnectionError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.TimeoutError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.VolleyMultipartRequest
import com.example.frontend.databinding.ActivityAdditionBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class AdditionActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAdditionBinding

    lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionBinding.inflate(layoutInflater)

        binding.addUpdateBtn.setOnClickListener {
            httpRequestSend()
        }

        binding.addImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            activityResult.launch(intent)
        }

        setContentView(binding.root)
    }



    fun httpRequestSend() {
        val request = object: StringRequest(
            Request.Method.POST,
            "http://172.22.3.83:3389/upload",
            Response.Listener<String> {
                Toast.makeText(this, "성공!.", Toast.LENGTH_LONG).show()
                finish()
            },
            Response.ErrorListener {
                Toast.makeText(this, "보내기에 실패했습니다.", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["userId"] = "12"
                params["title"] = binding.addName.text.toString()
                params["content"] = binding.addInform.text.toString()
                params["price"] = binding.addPrice.text.toString()
                params["numberOfImages"] = "0"
                params["image_1"] = ""
                params["image_2"] = ""
                params["image_3"] = ""
                params["image_4"] = ""
                params["image_5"] = ""
                params["video"] = ""
                params["xSize"] = binding.addXSize.text.toString()
                params["ySize"] = binding.addYSize.text.toString()
                params["zSize"] = binding.addZSize.text.toString()

                params["title"]?.let { Log.d("제목", it) }
                params["xSize"]?.let { Log.d("크기", it) }
                params["price"]?.let { Log.d("가격", it) }

                return params
            }
        }
        // 이미 받은 응답 재사용
        request.setShouldCache(false)
        MainActivity.requestQueue?.add(request)
    }

    fun httpRequestSend_test() {
        val request = object : VolleyMultipartRequest(
            Request.Method.POST,
            "http://172.22.3.83:3389/upload",
            Response.Listener {
                Toast.makeText(this, "성공!.", Toast.LENGTH_LONG).show()
                finish()
            },
            Response.ErrorListener {
                Toast.makeText(this, "보내기에 실패했습니다.", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["userId"] = "12"
                params["title"] = binding.addName.text.toString()
                params["content"] = binding.addInform.text.toString()
                params["price"] = binding.addPrice.text.toString()
                params["numberOfImages"] = "0"
                params["xSize"] = binding.addXSize.text.toString()
                params["ySize"] = binding.addYSize.text.toString()
                params["zSize"] = binding.addZSize.text.toString()

                return params
            }

            override fun getByteData(): Map<String, DataPart>? {
                return super.getByteData()
            }
        }
        // 이미 받은 응답 재사용
        request.setShouldCache(false)
        MainActivity.requestQueue?.add(request)
    }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {

        if(it.resultCode == RESULT_OK && it.data != null) {
            uri = it.data!!.data!!

            Glide.with(this)
                .load(uri)
                .into(binding.addImg)
        }

    }
}