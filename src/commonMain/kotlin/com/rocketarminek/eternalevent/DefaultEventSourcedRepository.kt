package com.rocketarminek.eternalevent

class DefaultEventSourcedRepository<Id, Event : Identifiable<Id>, Resource : EventSourcedAggregate<Id, Event>>(
    private val eventStore: EventStore<Id, Event>,
    private val factory: (events: List<Event>) -> Resource
) : EventSourcedRepository<Id, Event, Resource> {

    override fun save(resource: Resource) = this.eventStore.save(resource.commit())

    override fun get(id: Id): Resource {
        val events = this.eventStore.load(id)
        if (events.isEmpty()) throw EventSourcedAggregateNotFound("Resource with $id not found!")

        return this.factory(this.eventStore.load(id))
    }

}
class EventSourcedAggregateNotFound(message: String): IllegalArgumentException(message)
