package com.rocketarminek.eternalevent

interface EventStore<StreamId, Event: IdentifiableStream<StreamId>> {
    fun save(events: List<Event>)
    fun load(streamId: StreamId): List<Event>
}
