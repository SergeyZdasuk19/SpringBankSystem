package bankService.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Currency implements GrantedAuthority {
    BYN, USD, EUR;

    @Override
    public String getAuthority() {
        return name();
    }
}
