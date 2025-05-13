package com.dekanat.dao;

import com.dekanat.model.Group;
import com.dekanat.model.User;
import java.util.Map;
import java.util.TreeMap;

public class DataBaseInMemory {
    private final Map<Integer, Group> groups;
    private final Map<Integer, User> users;
    private Integer gId;
    private Integer uId;
    

    public DataBaseInMemory() {
        groups = new TreeMap<>();
        users = new TreeMap<>();
        gId = 0;
        uId = 0;
    }

    public IDaoFactory getDaoFactory() {
        return new DaoFactory(this);
    }  

    public Map<Integer, Group> getGroups() {
        return groups;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Integer getgId() {
        return gId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
    
}
