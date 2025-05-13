package com.dekanat.dao;

import com.dekanat.model.User;
import java.util.Collection;

public interface IUserDao {
    User getByLogin(String login);

    void create(User user);

    void delete(int Id);
    
    User read(int Id);
    
    Collection<User> getAll();
}
