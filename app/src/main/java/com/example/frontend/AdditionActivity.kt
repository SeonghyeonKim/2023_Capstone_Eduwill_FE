package com.example.frontend

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.frontend.databinding.ActivityAdditionBinding
import org.json.JSONArray

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
            "http://172.22.54.97:3389/upload",
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
                params["userId"] = "10"
                params["title"] = binding.addName.toString()
                params["content"] = binding.addInform.toString()
                params["price"] = binding.addPrice.toString()
                params["numberOfImages"] = "0"
                params["video"] = uri.toString()
                params["xSize"] = binding.addXSize.toString()
                params["ySize"] = binding.addYSize.toString()
                params["zSize"] = binding.addZSize.toString()

                return params
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