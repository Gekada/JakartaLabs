package com.example.lb4.Common.Data.Dao;

import com.example.lb4.Common.Data.Entity.Group;
import com.example.lb4.Common.Data.Entity.Student;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class StudentDao {

    @PersistenceContext
    private EntityManager em;

    public void create(Student student) {
        em.persist(student);
    }

    public Student read(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> getPage(int page, int size) {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Student> getPageFiltered(String name, String email, String groupName, int page, int size) {
        StringBuilder queryBuilder = new StringBuilder("SELECT s FROM Student s WHERE 1=1");
        if (name != null && !name.isEmpty()) {
            queryBuilder.append(" AND LOWER(s.name) LIKE LOWER(:name)");
        }
        if (email != null && !email.isEmpty()) {
            queryBuilder.append(" AND LOWER(s.email) LIKE LOWER(:email)");
        }
        if (groupName != null && !groupName.isEmpty()) {
            queryBuilder.append(" AND LOWER(s.group.name) LIKE LOWER(:groupName)");
        }

        TypedQuery<Student> query = em.createQuery(queryBuilder.toString(), Student.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (email != null && !email.isEmpty()) {
            query.setParameter("email", "%" + email + "%");
        }
        if (groupName != null && !groupName.isEmpty()) {
            query.setParameter("groupName", "%" + groupName + "%");
        }

        return query.setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public void update(Student student) {
        em.merge(student);
    }

    public void delete(Long id) {

        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transferStudentToGroup(int studentId, int newGroupId) {
        Student student = em.find(Student.class, studentId);
        Group newGroup = em.find(Group.class, newGroupId);

        if (student == null || newGroup == null) {
            throw new IllegalArgumentException("User or group not found");
        }

        student.setGroup(newGroup);
        em.merge(student);
    }

    public List<Student> searchStudents(String name, String groupName) {
        String jpql = "SELECT s FROM Student s WHERE s.name LIKE :name OR s.group.name LIKE :gname";
        return em.createQuery(jpql, Student.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("gname", "%" + groupName + "%")
                .getResultList();
    }

}

