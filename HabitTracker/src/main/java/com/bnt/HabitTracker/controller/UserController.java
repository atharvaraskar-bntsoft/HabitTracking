package com.bnt.HabitTracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.bnt.HabitTracker.model.Users;
import com.bnt.HabitTracker.service.UserService;

@RestController
@RequestMapping("/habittrackerapi/users")
public class UserController {

     @Autowired
      UserService userService;
      
      Logger logger=LoggerFactory.getLogger(UserController.class);

      @PostMapping
      public ResponseEntity<Users> createUser( @RequestBody Users user){
         logger.info("Creating new user  with data: {}", user);
         Users createdUser=userService.saveUser(user);
         logger.info("Created user with ID: {}", createdUser.getId());
         return new ResponseEntity<Users>(createdUser,HttpStatus.CREATED);
        }

       
        
     @PostMapping("/login") // Adjusted to /login for authentication
     public ResponseEntity<Users> loginUser(@RequestBody Users user) {
        logger.info("Logging in user with username: {}", user.getUsername());
        Users authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
      }
      
      @GetMapping
      public ResponseEntity<List<Users>> getAllUsers(){
         logger.info("Fetching all users");
         List<Users> listOfAllUsers=userService.getAllUsers();
         logger.info("Fetched {} user ", listOfAllUsers.size());
         return new ResponseEntity<List<Users>>(listOfAllUsers,HttpStatus.FOUND);
        }
      
      @GetMapping("/{id}")
      public ResponseEntity<Optional<Users>> getUserById(@PathVariable("id")long id){
            logger.info("Fetching user  with ID: {}", id);
            Optional<Users>optionalUser=userService.getUserById(id);
            logger.info("Fetched user : {}",optionalUser.get());
            return new ResponseEntity<Optional<Users>>(optionalUser,HttpStatus.FOUND);
          }    

      @PutMapping  
      public ResponseEntity<Users> updateUser(@RequestBody Users user){
         logger.info("Updating user  with ID: {}", user.getId());
         Users updatedUser=userService.saveUser(user);
         logger.info("Updated user : {}", updatedUser);
         return new ResponseEntity<Users>(updatedUser,HttpStatus.OK);
       }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteUser(@PathVariable("id")long id){
         logger.info("Deleting user with ID: {}", id);
         userService.deleteUser(id);
         logger.info("Deleted user with ID: {}", id);
         return new ResponseEntity<>("User Deleted Succefully",HttpStatus.OK);
       }

}
