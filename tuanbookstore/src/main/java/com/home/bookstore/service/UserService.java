package com.home.bookstore.service;

import com.home.bookstore.model.Author;
import com.home.bookstore.model.User;
import com.home.bookstore.repositories.AuthorRepository;
import com.home.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder;


    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User get(Long id){
        return userRepository.findById(id).get();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
    }

}
