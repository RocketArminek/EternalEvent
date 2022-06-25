package com.rocketarminek.eternalevent

interface EventStore<Id, Event: Identifiable<Id>> {
    fun save(events: List<Event>)
    fun load(id: Id): List<Event>
}
