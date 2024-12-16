package tn.example.thebackend.cotrollers;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome houssem-eddin";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user){
        return service.addUser(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers(){
        return service.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User getUserById(@PathVariable int id){
        return service.getUser(id);
    }

    @PostMapping("/authentificate")
    public String authentificateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(authRequest.getEmail());
        else
            throw new UsernameNotFoundException("invalid user request !");
    }
}
