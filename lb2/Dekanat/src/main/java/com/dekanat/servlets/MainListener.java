package com.dekanat.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.dekanat.dao.*;
import com.dekanat.services.*;

public class MainListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        DataBaseInMemory db = new DataBaseInMemory();
        TestDataGenerator.generateData(db);
        
        IDaoFactory daoFactory = db.getDaoFactory();

        IGroupService groupService = new GroupService(daoFactory);
        arg0.getServletContext().setAttribute("groupServ", groupService);

        IUserService userService = new UserService(daoFactory);
        arg0.getServletContext().setAttribute("userServ", userService);
        
        arg0.getServletContext().setAttribute("free", userService.filterUsersWithoutGroup());
        }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
