package com.example.lb4.Common.Data.Dao;

import com.example.lb4.Common.Data.Entity.Group;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class GroupDao {

    @PersistenceContext
    private EntityManager em;

    public void create(Group group) {
        em.persist(group);
    }

    public Group read(int id) {
        return em.find(Group.class, id);
    }

    public void update(Group group) {
        em.merge(group);
    }

    public void delete(int id) {
        Group group = read(id);
        if (group != null) em.remove(group);
    }

    public List<Group> getAll() {
        return em.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }
}

