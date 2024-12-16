package tn.example.thebackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    String name;
    String email;
    String password;
    String roles;


    public enum Gender{
        Homme,Femme,Non_Binaire,Genre_Fluide,Agender,Bigenre,Trigender,Genderqueer
    }

}
