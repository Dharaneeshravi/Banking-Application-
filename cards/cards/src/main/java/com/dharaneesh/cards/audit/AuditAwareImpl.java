package com.dharaneesh.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * This method returns the current auditor's name, which is hardcoded as "CARDS_MS" in this implementation.
     * In a real-world application, this could be dynamically fetched from the security context or user session.
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
}
