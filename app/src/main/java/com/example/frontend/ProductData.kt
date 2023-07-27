package com.example.frontend

import java.io.Serializable

data class ProductData  (
    val name : String,
    val inform : String,
    val img : Int
) : Serializable