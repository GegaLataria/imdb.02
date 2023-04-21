package com.example.imdb_02.serviceImpl;

import com.example.imdb_02.entity.User;
import com.example.imdb_02.repo.UserRepo;
import com.example.imdb_02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @Override
    public void addUser(User user) throws Exception {
        if(userRepo.existsByUserName(user.getUserName())){
            throw new Exception("User already exists");
        }
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        Optional<User> currentUser = userRepo.findById(id);
        if(currentUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User newUser = currentUser.get();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        userRepo.save(newUser);
    }
}
