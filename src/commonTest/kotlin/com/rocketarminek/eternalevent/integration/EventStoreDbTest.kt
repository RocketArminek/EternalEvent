package com.rocketarminek.eternalevent.integration

import com.eventstore.dbclient.EventStoreDBClient
import com.eventstore.dbclient.EventStoreDBConnectionString
import com.rocketarminek.eternalevent.*
import com.rocketarminek.eternalevent.Created
import com.rocketarminek.eternalevent.Event
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals

class EventStoreDbTest {
    @Test
    fun `it stores events in event store db`() {
        val settings = EventStoreDBConnectionString.parseOrThrow("esdb://event-store:2113?tls=false")
        val client = EventStoreDBClient.create(settings)
        val eventStore = EventStoreDb<String, Event>(client, GsonSerializer()) { events, serializer ->
            events.map {
                when(it.eventType) {
                    Created::class.qualifiedName -> serializer.deserialize(String(it.eventData), Created::class.java)
                    else -> null
                }
            }.filterNotNull()
        }
        val firstId = UUID.randomUUID().toString()
        val secondId = UUID.randomUUID().toString()
        val thirdId = UUID.randomUUID().toString()
        val events = listOf(
            Created(firstId, "Jon Snow"),
            Created(secondId, "Pickle Rick"),
            Created(secondId, "Morty"),
            Created(thirdId, "Mike"),
            Created(thirdId, "Jessie"),
        )
        eventStore.save(events)

        assertEquals(listOf(Created(firstId, "Jon Snow")), eventStore.load(firstId))
        assertEquals(listOf(Created(secondId, "Pickle Rick"), Created(secondId, "Morty")), eventStore.load(secondId))
        assertEquals(listOf(Created(thirdId, "Mike"), Created(thirdId, "Jessie")), eventStore.load(thirdId))
    }
}
