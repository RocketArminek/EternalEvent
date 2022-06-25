package com.rocketarminek.eternalevent

class InMemoryEventStore<Id, Event: Identifiable<Id>>: EventStore<Id, Event> {
    private val memory: MutableList<Event> = mutableListOf()

    override fun save(events: List<Event>) { this.memory.addAll(events) }
    override fun load(id: Id): List<Event> = this.memory.filter { it.id() == id }
}
