package com.dekanat.services;

import com.dekanat.model.User;
import java.util.Collection;

public interface IUserService {
    
    User getByLogin(String login);
    
    boolean checkPassword(User user, String password);
    
    Collection<User> filterUsersWithoutGroup();
}
