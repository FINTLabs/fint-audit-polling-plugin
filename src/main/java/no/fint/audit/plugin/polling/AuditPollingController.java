package no.fint.audit.plugin.polling;

import lombok.extern.slf4j.Slf4j;
import no.fint.audit.model.AuditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@CrossOrigin
@RestController("/admin/audit")
public class AuditPollingController {

    @Autowired
    private AuditPollingRepository repository;

    @GetMapping
    public List<AuditEvent> getAuditEvents(@RequestParam long index) {
        return repository.getEvents(new AtomicLong(index));
    }
}