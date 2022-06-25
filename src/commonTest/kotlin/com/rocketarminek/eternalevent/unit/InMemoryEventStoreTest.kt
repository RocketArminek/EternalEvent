package com.rocketarminek.eternalevent.unit

import com.rocketarminek.eternalevent.*
import kotlin.test.*

class InMemoryEventStoreTest {
    @Test
    fun `it stores events in memory`() {
        val eventStore = InMemoryEventStore<String, Event>()
        val events = listOf(
            Created("#1", "Jon Snow"),
            Created("#2", "Pickle Rick"),
            Created("#2", "Morty"),
            Created("#3", "Mike"),
            Created("#3", "Jessie"),
        )
        eventStore.save(events)

        assertEquals(listOf(Created("#1", "Jon Snow")), eventStore.load("#1"))
        assertEquals(listOf(Created("#2", "Pickle Rick"), Created("#2", "Morty")), eventStore.load("#2"))
        assertEquals(listOf(Created("#3", "Mike"), Created("#3", "Jessie")), eventStore.load("#3"))
    }
}
