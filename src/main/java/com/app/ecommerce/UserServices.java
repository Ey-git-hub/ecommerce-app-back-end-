package com.app.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {
    private UserRepository userRepository;

    //    private List<User> userList = new ArrayList<>();
//    private Long nextid=1L;
    //adding the user
    public void addUsers(User user) {

        userRepository.save(user);
    }

    //update the user
    public boolean UpdateUsers(User updateUser, Long id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    userRepository.save(existingUser);
                    return true;

                }).orElse(false);

    }

    //fetching the users
    public List<User> fetchAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }
}