package com.bnt.HabitTracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.HabitTracker.exception.DataIsNotPresentException;
import com.bnt.HabitTracker.exception.DuplicateDataException;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.exception.ValidationException;
import com.bnt.HabitTracker.model.Users;
import com.bnt.HabitTracker.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Users saveUser(Users user) {
        logger.info("Saving user: {}", user);
        Optional<Users> optionalUser =userRepository.findByUsername(user.getUsername()); 
        if(optionalUser.isPresent()){
            logger.warn("Duplicate user  detected: {}", user.getUsername());
            throw new DuplicateDataException("User is already Present");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            throw new ValidationException("Username must be between 3 and 50 characters");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (user.getPassword().length() < 8 || user.getPassword().length() > 100) {
            throw new ValidationException("Password must be between 8 and 100 characters");
        }
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());
        
        Users savedUser = userRepository.save(user);
        logger.info("Saved user  with ID: {}", savedUser.getId());   
        return savedUser;
    }

    @Override
    public Users authenticateUser(String username, String password) {
            logger.info("Authenticating user with username: {}", username);
            Optional<Users> optionalUser = userRepository.findByUsername(username);
            
            if (optionalUser.isPresent()) {
                Users user = optionalUser.get();
                if (user.getPassword().equals(password)) {
                    logger.info("User authenticated successfully");
                    return user;
                } else {
                    logger.warn("Invalid password for user: {}", username);
                    throw new IdNotFoundException("Invalid password");
                }
            } else {
                logger.warn("User not found with username: {}", username);
                throw new IdNotFoundException("User not found");
            }      
     }

    @Override
    public Users updateUser(Users user) {
        logger.info("Updating user  with ID: {}", user.getId());
        Optional<Users>optionalUser= userRepository.findById(user.getId());
         if (!optionalUser.isPresent()) {
            logger.warn("User  not found with ID: {}", user.getId());
            throw new IdNotFoundException("UserRole not found with ID: " + user.getId());
         }
         user.setUpdatedOn(LocalDateTime.now());
         Users updatedUser = userRepository.save(user);
         logger.info("Updated user: {}", updatedUser);
         return updatedUser;
    }

    @Override
    public List<Users> getAllUsers() {
        logger.info("Fetching all users");
        List<Users> users = userRepository.findAll();   
        if (users.isEmpty()) {
            logger.warn("No user found");
            throw new DataIsNotPresentException("No users found");
        }
       logger.info("Fetched {} user roles", users.size()); 
       return users;
    }

    @Override
    public Optional<Users> getUserById(long id) {
        logger.info("Fetching user with ID: {}", id);
        Optional<Users>optionalUser= userRepository.findById(id);
         if (!optionalUser.isPresent()) {
            logger.warn("User  not found with ID: {}", id);
            throw new IdNotFoundException("User not found with ID: " + id);
         }
         logger.info("Fetched user role {}");
         return optionalUser;
    }

    @Override
    public void deleteUser(long id) {
        logger.info("Deleting user  with ID: {}", id);
        Optional<Users>optionalUser= userRepository.findById(id);
         if (!optionalUser.isPresent()) {
            logger.warn("User not found with ID: {}", id);
            throw new IdNotFoundException("User not found with ID: " + id);
         }
         logger.info("User  deleted with ID: {}", id);
         userRepository.deleteById(id);
    }

    

}
