package com.example.frontend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView

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

        datas.apply {
            add(ProductData(img = R.drawable.product1, name = "1", inform = "1"))
            add(ProductData(img = R.drawable.product2, name = "2", inform = "2"))
            add(ProductData(img = R.drawable.product3, name = "3", inform = "3"))
            add(ProductData(img = R.drawable.product4, name = "4", inform = "4"))
            add(ProductData(img = R.drawable.product5, name = "5", inform = "5"))
            add(ProductData(img = R.drawable.product6, name = "6", inform = "6"))
            add(ProductData(img = R.drawable.product7, name = "7", inform = "7"))
            add(ProductData(img = R.drawable.product8, name = "8", inform = "8"))

            adapter.datas = datas
            adapter.notifyDataSetChanged()
        }
    }
}