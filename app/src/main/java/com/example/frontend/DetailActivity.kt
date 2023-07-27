package com.example.frontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.frontend.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    lateinit var datas : ProductData
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        datas = intent.getSerializableExtra("data") as ProductData

        Glide.with(this).load(datas.img).into(binding.detailImg)
        binding.detailName.text = datas.name
        binding.detailInform.text = datas.inform
        binding.detailPrice.text = DecimalFormat("###,###").format(datas.price).toString() + "원"

        setContentView(binding.root)
    }
}
