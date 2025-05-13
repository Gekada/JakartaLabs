package com.dekanat.dao;

import com.dekanat.model.Group;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class GroupDao implements IGroupDao{
    protected DataBaseInMemory database;

    public GroupDao(DataBaseInMemory database) {
        this.database = database;
    }

    @Override
    public Collection<Group> searchByTitle(String arg0) {
        return database.getGroups().values()
                .stream()
                .filter(g -> g.getName().toLowerCase().equals(arg0.toLowerCase()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Collection<Group> searchByStudentSurname(String arg0) {
        return database.getGroups().values().stream()
                .filter(i -> (i.getStudents().stream()
                                    .filter(j->j.getSurname().equals(arg0))
                                    .count() >= 1))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public void create(Group arg0) {
        arg0.setId(database.getgId() + 1);
        database.setgId(arg0.getId());
        database.getGroups().put(arg0.getId(), arg0);
    }

    @Override
    public void delete(int arg0) {
         database.getGroups().remove(arg0);
    }

    @Override
    public Group read(int arg0) {
        return database.getGroups().get(arg0);
    }

    @Override
    public Collection<Group> getAll() {
        return database.getGroups().values();
    }
}
