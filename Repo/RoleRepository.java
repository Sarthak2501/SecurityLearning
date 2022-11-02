package com.example.demo.Repo;

import com.example.demo.Dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao,Long> {
    RoleDao findByName(String name);
}
