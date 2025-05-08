package com.example.lb4.Common.Data.Dao;

import com.example.lb4.Common.Data.Entity.Group;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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

    public List<Group> getPage(int page, int size) {
        return em.createQuery("SELECT g FROM Group g", Group.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Group> getPageFiltered (String name, int page, int size) {
        StringBuilder queryBuilder = new StringBuilder("SELECT g FROM Group g WHERE 1=1");
        if (name != null && !name.isEmpty()) {
            queryBuilder.append(" AND LOWER(g.name) LIKE LOWER(:name)");
        }

        TypedQuery<Group> query = em.createQuery(queryBuilder.toString(), Group.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }

        return query.setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
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

