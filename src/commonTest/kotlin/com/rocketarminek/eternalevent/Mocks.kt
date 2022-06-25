package com.rocketarminek.eternalevent

internal class User: EventSourcedAggregate<String, Event> {
    lateinit var id: String
        private set
    lateinit var name: String
        private set

    constructor(events: List<Event>): super(events)
    constructor(id: String, name: String) { this.apply(Created(id, name)) }

    override fun handle(event: Event) {
        when (event) {
            is Created -> handle(event)
        }
    }

    override fun id(): String = this.id

    private fun handle(event: Created) {
        this.id = event.id
        this.name = event.name
    }
}

internal data class Created(val id: String, val name: String): Event {
    override fun id(): String = this.id
}

internal interface Event: Identifiable<String>
