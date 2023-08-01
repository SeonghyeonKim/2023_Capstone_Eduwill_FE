package com.example.frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ProductAdapter
    val datas = mutableListOf<ProductData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {
        adapter = ProductAdapter(this)
        val ProductList: RecyclerView = findViewById(R.id.ProductList)
        ProductList.adapter = adapter

        val json = assets.open("product_datas.json").reader().readText()

        val productCount = JSONObject(json).getInt("count")
        val productDataArray = JSONObject(json).getJSONArray("product")

        datas.apply {
            for(i: Int in 0..productCount-1) {
                val jsonObject = productDataArray.getJSONObject(i)

                add(ProductData(
                    id = jsonObject.getInt("id"),
                    img = jsonObject.getString("img"),
                    name = jsonObject.getString("name"),
                    inform = jsonObject.getString("inform"),
                    price = jsonObject.getInt("price"),
                    xSize = jsonObject.getInt("xSize"),
                    ySize = jsonObject.getInt("ySize"),
                    zSize = jsonObject.getInt("zSize"),
                    ))
            }

            adapter.datas = datas
            adapter.notifyDataSetChanged()
        }
    }
}