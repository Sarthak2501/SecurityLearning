package com.example.demo.Dto;

import com.example.demo.Dao.RoleDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    private Long id;
    private String name;
    private String userName;
    private String password;

    private Collection<RoleDao> roles = new ArrayList<>();
}
