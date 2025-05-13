package com.dekanat.services;

import com.dekanat.dao.IDaoFactory;
import com.dekanat.model.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class UserService implements IUserService{

    IDaoFactory daoFactory;
    
    public UserService(IDaoFactory dao) {
        this.daoFactory = dao;
    }
    
    @Override
    public User getByLogin(String login) {
        return daoFactory.getUserDao().getByLogin(login);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public Collection<User> filterUsersWithoutGroup() {
        Collection<User> usersInGroups = daoFactory.getGroupDao()
                                            .getAll()
                                            .stream()
                                            .flatMap(i->i.getStudents().stream())
                                            .collect(Collectors.toCollection(HashSet::new));
        return daoFactory.getUserDao().getAll().stream()
                                    .filter(i->(!usersInGroups.contains(i))&(!i.isAdmin()))
                                    .collect(Collectors.toCollection(HashSet::new));
    }
    
}
