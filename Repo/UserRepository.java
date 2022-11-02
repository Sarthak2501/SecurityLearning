package com.example.demo.Repo;

import com.example.demo.Dao.AppUserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUserDao,Long> {
    AppUserDao findByUserName(String name);
}
