package com.example.demo.service;

import com.example.demo.Dao.AppUserDao;
import com.example.demo.Dao.RoleDao;
//import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    AppUserDao saveUser(AppUserDao user);
    RoleDao saveRole(RoleDao role);

    void addRoleToUser(String username,String roleName);

    AppUserDao getUsers(String username);

    List<AppUserDao> getUsers();
}
