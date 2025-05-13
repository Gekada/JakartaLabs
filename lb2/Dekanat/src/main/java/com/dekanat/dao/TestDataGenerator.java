package com.dekanat.dao;

import com.dekanat.model.*;

public class TestDataGenerator {
    public static void generateData(DataBaseInMemory db){
        User user1 = new User(1, "user1","1111","CAPS","CAPSOVYCH", true);
        User user2 = new User(2, "user2","1111","Maksym","Stelmashenko", false);
        User user3 = new User(3, "user3","1111","Nazar","Nikitchenko", false);
        User user4 = new User(4, "user4","1111","Mykhailo","Ilnytskyi", false);
        User user5 = new User(5, "user5","1111","Herman","Kaydanyuk", false);
        User user6 = new User(6, "user6","1111","Kotleta","Kotletivna", false);
        User user7 = new User(7, "user7","1111","Mnyaso","Mnyasovych", false);
        
        db.getUsers().put(user1.getId(), user1);
        db.getUsers().put(user2.getId(), user2);
        db.getUsers().put(user3.getId(), user3);
        db.getUsers().put(user4.getId(), user4);
        db.getUsers().put(user5.getId(), user5);
        db.getUsers().put(user6.getId(), user6);
        db.getUsers().put(user7.getId(), user7);
        
        db.setuId(6);
        
        Group gr1 = new Group(1, "IT-41mn");
        Group gr2 = new Group(2, "IM-41mn");
        Group gr3 = new Group(3, "IK-41mp");
        Group gr4 = new Group(4, "IM-41mn");
        
        gr1.getStudents().add(user2);
        gr1.getStudents().add(user6);
        
        gr2.getStudents().add(user5);
        gr2.getStudents().add(user3);
        gr2.getStudents().add(user4);
        
        db.getGroups().put(gr1.getId(), gr1);
        db.getGroups().put(gr2.getId(), gr2);
        
        db.setgId(2);
    }
}
