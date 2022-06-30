package com.rocketarminek.eternalevent

interface EventSourcedRepository<Id, Event : Identifiable<Id>, Resource : EventSourcedAggregate<Id, Event>> {
    fun save(resource: Resource)
    fun get(id: Id): Resource
}
