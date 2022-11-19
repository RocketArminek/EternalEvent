package com.rocketarminek.eternalevent

interface Serializer {
    fun serialize(data: Any): String
    fun <T> deserialize(rawData: String, type: Class<T>): T
}
