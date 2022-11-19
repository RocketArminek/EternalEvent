package com.rocketarminek.eternalevent

class DefaultEventSourcedRepository<StreamId, Event : IdentifiableStream<StreamId>, Resource : EventSourcedAggregate<Event>>(
    private val eventStore: EventStore<StreamId, Event>,
    private val factory: (events: List<Event>) -> Resource
) : EventSourcedRepository<StreamId, Event, Resource> {

    override fun save(resource: Resource) = this.eventStore.save(resource.commit())

    override fun get(streamId: StreamId): Resource {
        val events = this.eventStore.load(streamId)
        if (events.isEmpty()) throw EventSourcedAggregateNotFound("Resource for $streamId not found!")

        return this.factory(this.eventStore.load(streamId))
    }

}
