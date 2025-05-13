package com.dekanat.services;

import com.dekanat.dao.IDaoFactory;
import com.dekanat.model.Group;
import java.util.Collection;


public class GroupService implements IGroupService
{
    IDaoFactory daoFactory;
    
    public GroupService(IDaoFactory dao) {
        this.daoFactory = dao;
    }

    @Override
    public Collection<Group> searchByTitle(String name) {
        return daoFactory.getGroupDao().searchByTitle(name);
    }

    @Override
    public Collection<Group> searchByStudentSurname(String surname) {
        return daoFactory.getGroupDao().searchByStudentSurname(surname);
    }

    @Override
    public void create(String name) {
        daoFactory.getGroupDao().create(new Group(-1, name));
    }

    @Override
    public void delete(int Id) {
        daoFactory.getGroupDao().delete(Id);
    }

    @Override
    public Group read(int Id) {
       return daoFactory.getGroupDao().read(Id);
    }
    

    @Override
    public Collection<Group> getAll() {
        return daoFactory.getGroupDao().getAll();
    }
    
    @Override
    public void addUser(Integer Id, Group group){
        group.getStudents().add(daoFactory.getUserDao().read(Id));
    }
    
    @Override
    public void deleteUser(Integer Id, Group group){
        group.getStudents().remove(daoFactory.getUserDao().read(Id));
    }
    
}
