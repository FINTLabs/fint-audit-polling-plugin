package no.fint.audit.plugin.polling;

import lombok.Data;
import no.fint.audit.model.AuditEvent;

import java.util.List;

@Data
public class AuditResult {
    private long index;
    private List<AuditEvent> events;
}
