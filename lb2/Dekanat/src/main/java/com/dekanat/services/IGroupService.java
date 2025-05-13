package com.dekanat.services;

import com.dekanat.model.Group;
import java.util.Collection;

public interface IGroupService {
    Collection<Group> searchByTitle(String name);
    
    Collection<Group> searchByStudentSurname(String name);

    void create(String name);

    void delete(int Id);
    
    Group read(int Id);
    
    Collection<Group> getAll();
    
    void addUser(Integer Id, Group group);
    
    void deleteUser(Integer Id, Group group);
}
