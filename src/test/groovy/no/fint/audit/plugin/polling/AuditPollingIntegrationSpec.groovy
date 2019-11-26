package no.fint.audit.plugin.polling

import no.fint.audit.FintAuditService
import no.fint.audit.plugin.polling.testutils.TestApplication
import no.fint.event.model.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

import java.util.concurrent.atomic.AtomicLong

@SpringBootTest(classes = TestApplication)
class AuditPollingIntegrationSpec extends Specification {

    @Autowired
    private FintAuditService fintAuditService

    @Autowired
    private AuditPollingRepository auditPollingRepository

    def "Persist an AuditEvent"() {
        given:
        def event = new Event('rogfk.no', 'FK', 'GET', 'C')
        def conditions = new PollingConditions(timeout: 10)

        when:
        fintAuditService.audit(event)

        then:
        conditions.eventually {
            def events = auditPollingRepository.getEvents(new AtomicLong(0))
            assert events.size() == 1
            assert events[0].corrId == event.corrId
        }
    }

}
