package com.example.demo.service;

import com.example.demo.Dao.AppUserDao;
import com.example.demo.Dao.RoleDao;
import com.example.demo.Repo.RoleRepository;
import com.example.demo.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserDao user = userRepository.findByUserName(username);
        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in database");
        } else{
            log.info("User found in the database {} ",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
        {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),authorities);
    }
//
//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public AppUserDao saveUser(AppUserDao appUser) {
        log.info("Saving new user {} to the database",appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public RoleDao saveRole(RoleDao role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {} to user {}",roleName,username);
        AppUserDao appUser = userRepository.findByUserName(username);
        RoleDao role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUserDao getUsers(String username) {
        log.info("fetching user {} from the database",username);
        return userRepository.findByUserName(username);
    }

    @Override
    public List<AppUserDao> getUsers() {
        log.info("fetching all user");
        return userRepository.findAll();
    }
}
