package tn.example.thebackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.example.thebackend.entites.User;
import tn.example.thebackend.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService{
    UserRepository userRepo;

    @Override
    public String ajouterUnUtilisateur(User user) {
        userRepo.save(user);

        return "bienvenue ";
    }
}
