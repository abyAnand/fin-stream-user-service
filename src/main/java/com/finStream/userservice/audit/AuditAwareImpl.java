package com.finStream.userservice.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Abi Anand  <a href="mailto:abianand382@gmail.com"></a>
 * @version 1.0.0
 * @since 1.0.0
 */

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }


}
