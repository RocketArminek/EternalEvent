package com.rocketarminek.eternalevent

import com.google.gson.Gson

class GsonSerializer: Serializer {
    override fun serialize(data: Any): String {
        return Gson().toJson(data)
    }

    override fun <T> deserialize(rawData: String, type: Class<T>): T {
        return Gson().fromJson(rawData, type)
    }
}
