package com.example.java_lessons_app.services;

import com.example.java_lessons_app.dto.UserAddDTO;
import com.example.java_lessons_app.dto.UserDTO;
import com.example.java_lessons_app.entities.News;
import com.example.java_lessons_app.entities.User;
import com.example.java_lessons_app.enums.Role;
import com.example.java_lessons_app.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User mapToEntity(UserAddDTO userAddDTO){
        List<News> newsList = new ArrayList<>();
        return User.builder()
                .email(userAddDTO.getEmail())
                .password(userAddDTO.getPassword())
                .role(Role.USER)
                .news(newsList)
                .build();
    }

    public UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .newsIdList(user.getNews().stream().map(News::getId).collect(Collectors.toList()))
                .build();
    }

    public List<UserDTO> getAll(){

        return usersRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO findById(Long id){

        return mapToDTO(usersRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public void add(UserAddDTO userAddDTO){
        usersRepository.save(mapToEntity(userAddDTO));
    }

    public Optional<User> update(@Valid UserAddDTO userAddDTO, Long id){
        Optional<User> userOptional = usersRepository.findById(id);
        User newUser = mapToEntity(userAddDTO);
        userOptional.ifPresent(user -> {
            usersRepository.deleteById(id);
            newUser.setId(id);
            usersRepository.save(newUser);
        });
        return userOptional;
    }

    public Optional<User> delete(Long id){
        Optional<User> userOptional = usersRepository.findById(id);
        userOptional.ifPresent(user -> {
            usersRepository.deleteById(id);
        });
        return userOptional;
    }


}
