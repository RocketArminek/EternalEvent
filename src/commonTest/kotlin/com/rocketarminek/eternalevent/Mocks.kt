package com.rocketarminek.eternalevent

internal class User: EventSourcedAggregate<Event> {
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

    private fun handle(event: Created) {
        this.id = event.id
        this.name = event.name
    }
}

internal data class Created(val id: String, val name: String): Event {
    override fun streamId(): String = this.id
}

internal interface Event: IdentifiableStream<String>
