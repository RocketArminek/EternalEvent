package com.rocketarminek.eternalevent.unit

import com.rocketarminek.eternalevent.*
import kotlin.test.*

class EventSourcedRepositoryTest {
    @Test
    fun `it stores resource`() {
        val eventStore = InMemoryEventStore<String, Event>()
        val repository = DefaultEventSourcedRepository(eventStore) { User(it) }
        eventStore.save(
            listOf(
                Created("#1", "Jon Snow"),
                Created("#2", "Pickle Rick"),
                Created("#3", "Morty"),
                Created("#4", "Mike"),
                Created("#5", "Jessie"),
            )
        )

        val user1 = repository.get("#1")
        assertEquals("Jon Snow", user1.name)
        assertEquals("#1", user1.id)

        val user2 = repository.get("#2")
        assertEquals("Pickle Rick", user2.name)
        assertEquals("#2", user2.id)

        val user3 = repository.get("#3")
        assertEquals("Morty", user3.name)
        assertEquals("#3", user3.id)

        val user4 = repository.get("#4")
        assertEquals("Mike", user4.name)
        assertEquals("#4", user4.id)

        val user5 = repository.get("#5")
        assertEquals("Jessie", user5.name)
        assertEquals("#5", user5.id)
    }
}
