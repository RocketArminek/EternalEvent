package com.rocketarminek.eternalevent.unit

import com.rocketarminek.eternalevent.Created
import com.rocketarminek.eternalevent.GsonSerializer
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GsonSerializerTest {
    @Test
    fun `it stores resource`() {
        val event = Created("#1", "test")
        val serializer = GsonSerializer()
        val serializedEvent = serializer.serialize(event)
        val deserializedEvent = serializer.deserialize(serializedEvent, Created::class.java)

        assertEquals("test", deserializedEvent.name)
    }
}