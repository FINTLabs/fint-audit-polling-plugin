package no.fint.audit.plugin.polling;

import lombok.extern.slf4j.Slf4j;
import no.fint.audit.model.AuditEvent;
import no.fint.event.model.Event;
import no.twingine.CircularBuffer;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class AuditPollingRepository {

    private CircularBuffer<AuditEvent> buffer;

    public AuditPollingRepository(int bufferSize) {
        buffer = new CircularBuffer<>(bufferSize);
    }

    public void audit(Event event, boolean clearData) {
        buffer.add(new AuditEvent(event, clearData));
    }

    public List<AuditEvent> getEvents(AtomicLong index) {
        return buffer.drain(index);
    }
}
