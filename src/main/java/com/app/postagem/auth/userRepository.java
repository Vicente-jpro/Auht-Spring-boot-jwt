package com.app.postagem.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface userRepository extends JpaRepository<users, Long> {
    UserDetails findByUsername(String username);
}
