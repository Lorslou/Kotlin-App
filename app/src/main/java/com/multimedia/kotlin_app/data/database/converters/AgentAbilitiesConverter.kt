package com.multimedia.kotlin_app.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.multimedia.kotlin_app.data.database.entities.AgentAbilitiesEntity

/**
 * This class is used as a type converter to allow Room to store and retrieve lists of
 * AgentAbilitiesEntity objects in the database
 */
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