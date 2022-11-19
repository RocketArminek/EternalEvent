package com.rocketarminek.eternalevent

class InMemoryEventStore<StreamId, Event: IdentifiableStream<StreamId>>: EventStore<StreamId, Event> {
    private val memory: MutableList<Event> = mutableListOf()

    override fun save(events: List<Event>) { this.memory.addAll(events) }
    override fun load(streamId: StreamId): List<Event> = this.memory.filter { it.streamId() == streamId }
}
