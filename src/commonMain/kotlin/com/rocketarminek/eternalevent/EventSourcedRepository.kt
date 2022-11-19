package com.rocketarminek.eternalevent

interface EventSourcedRepository<StreamId, Event : IdentifiableStream<StreamId>, Resource : EventSourcedAggregate<Event>> {
    fun save(resource: Resource)
    fun get(streamId: StreamId): Resource
}
