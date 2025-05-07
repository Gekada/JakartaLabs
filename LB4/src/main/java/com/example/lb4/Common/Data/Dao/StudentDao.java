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

