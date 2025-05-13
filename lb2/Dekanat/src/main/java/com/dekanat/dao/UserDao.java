package com.dekanat.dao;

import com.dekanat.model.User;
import java.util.Collection;

public class UserDao implements IUserDao{
    protected DataBaseInMemory database;

    public UserDao(DataBaseInMemory database) {
        this.database = database;
    }

    @Override
    public User getByLogin(String arg0) {
        return database.getUsers().values()
                .stream()
                .filter(user -> user.getLogin().equals(arg0))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(User arg0) {
        arg0.setId(database.getuId() + 1);
        database.setuId(arg0.getId());
        database.getUsers().put(arg0.getId(), arg0);
    }

    @Override
    public void delete(int arg0) {
        database.getUsers().remove(arg0);
    }

    @Override
    public User read(int arg0) {
         return database.getUsers().get(arg0);
    }

    @Override
    public Collection<User> getAll() {
        return database.getUsers().values();
    }
}
