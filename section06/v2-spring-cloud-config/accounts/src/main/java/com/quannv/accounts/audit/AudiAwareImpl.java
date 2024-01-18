package com.quannv.accounts.audit;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AudiAwareImpl implements AuditorAware<String> {

  /**
   * @return
   */
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("ACCOUNTS_MS");
  }
}
