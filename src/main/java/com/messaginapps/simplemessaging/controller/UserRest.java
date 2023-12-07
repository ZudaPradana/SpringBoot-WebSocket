package com.messaginapps.simplemessaging.controller;

import com.messaginapps.simplemessaging.model.User;
import com.messaginapps.simplemessaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserRest {
    private final UserService userService;

    @Autowired
    public UserRest(UserService userService){
        this.userService = userService;
    }

    @MessageMapping("/user.addUser") //menetapkan endpoint yang akan menangani pesan dari klien.
    @SendTo("/user/topic") //menentukan destinasi topik
    public User addUser(@Payload User user){ //payload =  isi pesan yang dikirim oleh klien.
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser") //menetapkan endpoint yang akan menangani pesan dari klien.
    @SendTo("/user/topic") //menentukan destinasi topik
    public User disconnect(@Payload User user){
        userService.disconnectUser(user);
        return user;
    }

    @GetMapping("/users") //menetapkan endpoint yang akan menangani pesan dari klien.
    public ResponseEntity<List<User>> findConnectedUser(){
        return ResponseEntity.ok(userService.findConnectedUser());
    }

}
