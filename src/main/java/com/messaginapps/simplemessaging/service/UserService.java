package com.messaginapps.simplemessaging.service;

import com.messaginapps.simplemessaging.model.Status;
import com.messaginapps.simplemessaging.model.User;
import com.messaginapps.simplemessaging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    //inject
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnectUser(User user){
        var userStatus = userRepository.findById(user.getNickName())
                .orElse(null);
        if (userStatus != null){
            user.setStatus(Status.OFFLINE);
            userRepository.save(userStatus);
        }
    }

    public List<User> findConnectedUser(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
