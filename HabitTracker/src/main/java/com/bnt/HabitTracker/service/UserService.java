package com.bnt.HabitTracker.service;

import com.bnt.HabitTracker.model.Users;
import java.util.*;

public interface UserService {

   public Users saveUser(Users user);

   public  Users updateUser(Users user);

   public List<Users> getAllUsers();

   public  Optional<Users> getUserById(long id);

   public void  deleteUser(long id);

  public Users authenticateUser(String username, String password);
    

}
