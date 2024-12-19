package tn.example.thebackend.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.example.thebackend.entities.User;
import tn.example.thebackend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initUser() {
        if (repository.findByEmail("houssem").isEmpty()) {
            User adminUser = new User();
            adminUser.setEmail("houssem");
            adminUser.setPassword(passwordEncoder.encode("houssem"));
            adminUser.setFirstName("houssem-eddin");
            adminUser.setLastName("admin");
            adminUser.setRoles("ROLE_ADMIN,ROLE_HOUSSEM");// just make sure that it contains ROLE_ADMIN to be an admin
            repository.save(adminUser);
        }else {
            System.out.println("User with email houssem already exists.");
        }

    }

//    List<User> userList=null;
//    @PostConstruct
//    public void loadUsersFromDB(){
//
//        userList= IntStream.rangeClosed(1,10)
//                .mapToObj(i -> User.builder()
//                        .userId(i)
//                        .name("user" + i)
//                        .email("email" + i)
//                        .password("password" + i)
//                        .build()
//                ).collect(Collectors.toList());
//    }

    public List<User> getUsers(){return repository.findAll();}

    public User getUser(int id){
//        return userList.stream()
//                .filter(user -> user.getUserId()==id)
//                .findAny()
//                .orElseThrow(()->new RuntimeException("product "+id+" not found."));
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User " + id + " not found."));
    }

    public String getRoleByEmail(String mail){
        Optional<User> user = repository.findByEmail(mail);
        return user.map(User::getRoles).orElseThrow( ()-> new UsernameNotFoundException("not found"));
    }

    public String addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        repository.save(user);
        return "user added succesfuly";
    }

}
