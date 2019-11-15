package jman.auth;

import jman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;
    private Collection<GrantedAuthority> authorities;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        jman.domain.User domainUser = userService.getUserByLogin(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        authorities = new HashSet<GrantedAuthority>();
        for (final String role : domainUser.getUserRole().split(",")) {

            if (role != null && !"".equals(role.trim())) {
                GrantedAuthority grandAuthority = new GrantedAuthority() {
                    private static final long serialVersionUID = 3958183417696804555L;

                    public String getAuthority() {
                        return role.trim();
                    }
                };
                authorities.add(grandAuthority);
                if (role.equals("ROLE_OKO")){
                    GrantedAuthority grandAuthorityD = new GrantedAuthority() {
                        private static final long serialVersionUID = 3958183417696804555L;

                        public String getAuthority() {
                            return "ROLE_ADMIN";
                        }
                    };
                    authorities.add(grandAuthorityD);
                }
                if (role.equals("ROLE_KIP")){
                    GrantedAuthority grandAuthorityD = new GrantedAuthority() {
                        private static final long serialVersionUID = 3958183417696804555L;

                        public String getAuthority() {
                            return "ROLE_ADMIN";
                        }
                    };
                    authorities.add(grandAuthorityD);
                    GrantedAuthority grandAuthorityD2 = new GrantedAuthority() {
                        private static final long serialVersionUID = 3958183417696804555L;

                        public String getAuthority() {
                            return "ROLE_OKO";
                        }
                    };
                    authorities.add(grandAuthorityD2);
                }
            }

        }

        return new User(
                domainUser.getUsername(),
                domainUser.getUserpass(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities
        );
    }

}

