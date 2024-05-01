package com.example.foodrecipeapp


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class Recipe (

    var img:String,

    var ing: String,
    var des:String,
    var tittle:String,

    var category: String
    ){
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid = 0
}