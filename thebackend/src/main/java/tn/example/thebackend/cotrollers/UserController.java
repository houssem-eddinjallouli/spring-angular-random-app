package tn.example.thebackend.cotrollers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tn.example.thebackend.dtos.AuthRequest;
import tn.example.thebackend.entities.User;
import tn.example.thebackend.services.JwtService;
import tn.example.thebackend.services.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void initRoleAndUser() {
        service.initUser();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome houssem-eddin";
    }

    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody User user) {
        String message = service.addUser(user);
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User getUserById(@PathVariable int id) {
        return service.getUser(id);
    }

    @PostMapping("/authentificate")
    public ResponseEntity<?> authentificateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String roles = service.getRoleByEmail(authRequest.getEmail());

            String token = jwtService.generateToken(roles, authRequest.getEmail());

            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
