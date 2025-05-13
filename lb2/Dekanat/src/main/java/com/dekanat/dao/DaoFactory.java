package com.dekanat.dao;

public class DaoFactory implements IDaoFactory{
    private final DataBaseInMemory database;
    private final IGroupDao groupDao;
    private final IUserDao userDao;

    DaoFactory(DataBaseInMemory database) {
        this.database = database;
        groupDao = new GroupDao(database);
        userDao = new UserDao(database);
    }

    @Override
    public IGroupDao getGroupDao() {
        return groupDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }
 
}
