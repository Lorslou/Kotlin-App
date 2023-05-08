package com.multimedia.kotlin_app.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.multimedia.kotlin_app.data.database.entities.AgentAbilitiesEntity

class AgentAbilitiesConverter {
    @TypeConverter
    fun fromListToString(list: List<AgentAbilitiesEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToList(value: String): List<AgentAbilitiesEntity> {
        val listConvert = object: TypeToken<List<AgentAbilitiesEntity>>(){}.type
        return Gson().fromJson(value,listConvert)
    }
}