package no.fint.audit.plugin.polling

import no.fint.audit.model.AuditEvent
import no.fint.event.model.Event
import no.fint.test.utils.MockMvcSpecification
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

import java.util.concurrent.atomic.AtomicLong

class AuditPollingControllerIntegrationSpec extends MockMvcSpecification {

    private AuditPollingController auditPollingController
    private AuditPollingRepository auditPollingRepository
    private MockMvc mockMvc

    void setup() {
        auditPollingRepository = Mock()
        auditPollingController = new AuditPollingController(repository: auditPollingRepository)
        mockMvc = standaloneSetup(auditPollingController)
    }

    def "Fetch audit events"() {
        when:
        def response = mockMvc.perform(get('/admin/audit?index=0'))

        then:
        noExceptionThrown()
        1 * auditPollingRepository.getEvents(_ as AtomicLong) >> [new AuditEvent(new Event('test.org', 'Spock', 'TEST_ALL', 'Anyone'))]
        response.andExpect(status().isOk())
                .andExpect(jsonPathSize('$', 1))
                .andExpect(jsonPathEquals('$[0].orgId', 'test.org'))
                .andDo(MockMvcResultHandlers.print())
    }
}
