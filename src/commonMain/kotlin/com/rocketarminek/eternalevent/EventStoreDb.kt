package com.rocketarminek.eternalevent

import com.eventstore.dbclient.EventData
import com.eventstore.dbclient.EventStoreDBClient
import com.eventstore.dbclient.ReadStreamOptions
import com.eventstore.dbclient.RecordedEvent

class EventStoreDb<StreamId, Event: IdentifiableStream<StreamId>>(
    private val client: EventStoreDBClient,
    private val serializer: Serializer,
    private val eventMapper: (events: List<RecordedEvent>, serializer: Serializer) -> List<Event>
): EventStore<StreamId, Event> {
    override fun save(events: List<Event>) {
        val streams = hashMapOf<String, MutableList<EventData>>()
        for (event in events) {
            val eventData = EventData.builderAsBinary(
                event::class.qualifiedName,
                this.serializer.serialize(event).toByteArray()
            ).build()
            val stream = streams[event.streamId().toString()]
            if (stream == null) {
                streams[event.streamId().toString()] = mutableListOf(eventData)
                continue
            }
            stream.add(eventData)
            streams[event.streamId().toString()] = stream
        }

        streams.forEach {
            this.client.appendToStream(it.key, it.value.iterator())
        }
    }

    override fun load(streamId: StreamId): List<Event> {
        val rawEvents = this.client.readStream(streamId.toString(), ReadStreamOptions.get().fromStart()).get().events
        val events = rawEvents.map { it.event }

        return this.eventMapper(events, this.serializer)
    }
}
