package com.atividade.spring.services;

import com.atividade.spring.controllers.CreateUserDTO;
import com.atividade.spring.controllers.UpdateUserDTO;
import com.atividade.spring.domain.User;
import com.atividade.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        var entity = new User(
                createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password()
        );

        var user = userRepository.save(entity);
        return user;
    }

    public Optional<User> getUserById(String id) {

        return userRepository.findById(Integer.parseInt(id));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUser(String id, UpdateUserDTO updateUserDTO) {
        var userId = Integer.parseInt(id);

        var userEntity = userRepository.findById(userId);

        if(userEntity.isPresent()) {
            var user = userEntity.get();

            if(updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }

            if(updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteUser(String id) {
        var userId = Integer.parseInt(id);

        var userExist = userRepository.existsById(userId);

        if(userExist) {
            userRepository.deleteById(userId);
        }
    }
}
