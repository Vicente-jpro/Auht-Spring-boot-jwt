package com.app.postagem.auth;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController{
    private final UserRole role = UserRole.USER;
    @Autowired
    userRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("register")
    public ResponseEntity Register(@RequestBody UserDto data) {
        if(repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String password = new BCryptPasswordEncoder().encode(data.password());

        users user = new users(data.username(), password, role);
        repository.save(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping("login")
    public ResponseEntity Login(@RequestBody UserDto data) {
        var UserNamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        var auth = authenticationManager.authenticate(UserNamePassword);

        return ResponseEntity.ok().build();
    }
}
