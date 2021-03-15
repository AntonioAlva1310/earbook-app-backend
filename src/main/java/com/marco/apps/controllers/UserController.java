package com.marco.apps.controllers;


import com.marco.apps.models.entity.User;
import com.marco.apps.services.IUserService;
import com.marco.apps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path= "api/v1")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
       return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User show(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userService.save(user);
    }


    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user, @PathVariable Long id){
        User currentUser = userService.findById(id);
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setIsPremium(user.getIsPremium());

        return userService.save(currentUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

}
