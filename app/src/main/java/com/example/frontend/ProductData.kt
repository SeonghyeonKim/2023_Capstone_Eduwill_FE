package com.example.frontend

import java.io.Serializable

data class ProductData  (
    val id : Int,
    val name : String,
    val inform : String,
    val img : String,
    val price : Int,
    val xSize : Int,
    val ySize : Int,
    val zSize : Int
) : Serializable