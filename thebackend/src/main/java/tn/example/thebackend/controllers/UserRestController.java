package tn.example.thebackend.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.example.thebackend.entites.User;
import tn.example.thebackend.services.IUserService;


@RestController
@RequestMapping("user")
public class UserRestController {
    @Autowired
    IUserService userService;

    @PostMapping("/add-simple-user")
    public String addSimpleUser(@RequestBody User user) {
        return userService.ajouterUnUtilisateur(user);
    }
}
