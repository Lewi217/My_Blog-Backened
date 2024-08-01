package DevLewi.My_Blog.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserLogin implements UserDetails {

    private final User user;

    public UserLogin(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Map user types to roles
        if (user.getUserType() != null) {
            switch (user.getUserType()) {
                case "0":
                    return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
                case "1":
                    return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
                default:
                    return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Return the actual password
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Return the actual username (e.g., email)
    }

    @Override
    public boolean isAccountNonExpired() {
        // Example implementation; modify as needed
        return true; // Always consider the account as non-expired
    }

    @Override
    public boolean isAccountNonLocked() {
        // Example implementation; modify as needed
        return true; // Always consider the account as non-locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Example implementation; modify as needed
        return true; // Always consider credentials as non-expired
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() != null && !"1".equals(user.getStatus()); // Assuming "1" means disabled
    }
}
