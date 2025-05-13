package com.dekanat.dao;

public interface IDaoFactory {
    IGroupDao getGroupDao();
    
    IUserDao getUserDao(); 
}
