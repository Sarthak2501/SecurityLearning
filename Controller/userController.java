package com.example.demo.Controller;

import com.example.demo.Dao.AppUserDao;
import com.example.demo.Dao.RoleDao;
import com.example.demo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class userController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<AppUserDao>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping()
    public ResponseEntity<AppUserDao> saveUser(@RequestBody AppUserDao appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1").toUriString());
        return ResponseEntity.created(null).body(userService.saveUser(appUser));
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDao> saveRole(@RequestBody RoleDao role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("{username}/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }


}

@Data
class RoleToUserForm{
    private String username;
    private String rolename;
}
