package com.antoniovieira.dogsapp.data.local.converters

import androidx.room.TypeConverter
import com.antoniovieira.dogsapp.data.model.Image
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun imageToJson(value: Image): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun imageFromJson(value: String): Image {
        return Gson().fromJson(value, Image::class.java)
    }

}