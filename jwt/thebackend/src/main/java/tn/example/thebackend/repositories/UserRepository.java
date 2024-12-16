package tn.example.thebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.example.thebackend.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
