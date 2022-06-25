package com.rocketarminek.eternalevent.unit

import com.rocketarminek.eternalevent.*
import kotlin.test.*

class EventSourcedAggregateTest {
    @Test
    fun `it stores events and apply state changes`() {
        val user = User("#1", "Jessie")

        assertEquals(listOf(Created("#1", "Jessie")), user.commit())
        assertEquals("Jessie", user.name)
    }

    @Test
    fun `its state can be loaded from events`() {
        val user = User(listOf(Created("#1", "Mike")))

        assertEquals(listOf(), user.commit())
        assertEquals("Mike", user.name)
    }

    @Test
    fun `it clears uncommitted changes after commit`() {
        val user = User("#1", "Jessie")
        user.commit()

        assertEquals(listOf(), user.commit())
        assertEquals("Jessie", user.name)
    }
}
