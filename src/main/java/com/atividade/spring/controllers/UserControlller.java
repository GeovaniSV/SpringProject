package com.atividade.spring.controllers;

import com.atividade.spring.domain.User;
import com.atividade.spring.services.UserServices;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControlller {

    private UserServices userServices;

    public UserControlller(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
        var user = userServices.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(user);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") String id) {
        var user = userServices.getUserById(id);
        return ResponseEntity.ok(user.get());
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userServices.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id,
                                           @RequestBody UpdateUserDTO updateUserDTO) {
        userServices.updateUser(id, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userServices.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
