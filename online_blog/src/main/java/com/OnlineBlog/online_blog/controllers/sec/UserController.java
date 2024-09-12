package com.OnlineBlog.online_blog.controllers.sec;

import com.OnlineBlog.online_blog.models.sec.Role;
import com.OnlineBlog.online_blog.models.sec.UserSec;
import com.OnlineBlog.online_blog.services.sec.IRoleService;
import com.OnlineBlog.online_blog.services.sec.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/getAll")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserSec>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/get/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserSec> findUserById(@PathVariable Long id){
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec user){

        Set<Role> roleList = new HashSet<>();
        Role readRole;

        user.setPassword(userService.encriptPassword(user.getPassword()));
        for (Role role : user.getRolesList()){
            readRole = roleService.findById(role.getId()).orElse(null);

            if (readRole != null){
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()){
            user.setRolesList(roleList);
            return ResponseEntity.ok(userService.save(user));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted.");
    }
}
