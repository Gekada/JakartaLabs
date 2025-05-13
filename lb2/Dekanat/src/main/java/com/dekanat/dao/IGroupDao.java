package com.dekanat.dao;

import com.dekanat.model.Group;
import java.util.Collection;

public interface IGroupDao {
    Collection<Group> searchByTitle(String login);
    
    Collection<Group> searchByStudentSurname(String login);

    void create(Group group);

    void delete(int Id);
    
    Group read(int Id);
    
    Collection<Group> getAll();
}
