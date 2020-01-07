package no.fint.audit.plugin.polling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.BufferOverflowException;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@CrossOrigin
@RestController("AuditEvents")
@RequestMapping("/admin/audit")
public class AuditPollingController {

    private final long epoch = System.currentTimeMillis();

    @Autowired
    private AuditPollingRepository repository;

    @GetMapping("/epoch")
    public long getEpoch() {
        return epoch;
    }

    @GetMapping("/events/{epoch}/{index}")
    public AuditResult getAuditEvents(
            @PathVariable long epoch,
            @PathVariable long index
    ) {
        if (epoch != this.epoch) {
            throw new BadRequestException("New epoch: " + this.epoch);
        }
        AuditResult result = new AuditResult();
        AtomicLong pos = new AtomicLong(index);
        try {
            result.setEvents(repository.getEvents(pos));
        } catch (IndexOutOfBoundsException e) {
            throw new BadRequestException(e.getMessage());
        } catch (BufferOverflowException ignored) {
        }
        result.setIndex(pos.get());
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadRequestException extends IllegalArgumentException {
        public BadRequestException(String s) {
            super(s);
        }
    }

}
