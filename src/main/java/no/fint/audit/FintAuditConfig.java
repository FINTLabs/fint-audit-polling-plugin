package no.fint.audit;

import lombok.extern.slf4j.Slf4j;
import no.fint.audit.plugin.polling.AuditPolling;
import no.fint.audit.plugin.polling.AuditPollingController;
import no.fint.audit.plugin.polling.AuditPollingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FintAuditConfig {

    @Value("${fint.audit.test-mode:false}")
    private String testMode;

    @Value("${fint.audit.polling.buffer-size:200000}")
    private int bufferSize;

    @Bean
    public AuditPolling auditPolling() {
        return new AuditPolling();
    }

    @Bean
    public AuditPollingRepository auditPollingRepository() {
        return new AuditPollingRepository(bufferSize);
    }

    @Bean
    public AuditPollingController auditPollingController() {
        return new AuditPollingController();
    }

}
