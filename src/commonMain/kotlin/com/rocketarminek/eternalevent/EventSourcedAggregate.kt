package com.rocketarminek.eternalevent

abstract class EventSourcedAggregate<Event>(): ContainsUncommittedChanges<Event> {
    private val uncommittedChanges: MutableList<Event> = mutableListOf()

    constructor(events: List<Event>) : this() {
        this.initState(events)
    }

    override fun commit(): List<Event> {
        val events = this.uncommittedChanges.toList()
        this.uncommittedChanges.clear()

        return events
    }

    protected abstract fun handle(event: Event)

    protected fun apply(event: Event) {
        this.handle(event)
        this.uncommittedChanges.add(event)
    }

    private fun initState(events: List<Event>) = events.forEach { this.handle(it) }
}
