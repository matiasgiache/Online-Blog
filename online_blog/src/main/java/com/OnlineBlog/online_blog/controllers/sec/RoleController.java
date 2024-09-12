package com.OnlineBlog.online_blog.controllers.sec;

import com.OnlineBlog.online_blog.models.sec.Permission;
import com.OnlineBlog.online_blog.models.sec.Role;
import com.OnlineBlog.online_blog.services.sec.IPermissionService;
import com.OnlineBlog.online_blog.services.sec.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/getAll")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/get/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/modify/{id}")
    //@PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Role> modifyRole(@PathVariable Long id, @RequestParam Long id_permission){

        Role role = roleService.findById(id).get();
        Set<Permission> permissionsList = role.getPermissionsList();
        Permission permission = permissionService.findById(id_permission).get();

        if (permissionsList.contains(permission)){
            permissionsList.remove(permission);
        } else {
            permissionsList.add(permission);
        }

        role.setPermissionsList(permissionsList);
        return ResponseEntity.ok(roleService.save(role));
    }

    @PostMapping("/create")
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Role> createRole(@RequestBody Role role){

        Set<Permission> permissionsList = new HashSet<>();
        Permission readPermission;

        for (Permission per : role.getPermissionsList()){
            readPermission = permissionService.findById(per.getId()).orElse(null);

            if (readPermission != null){
                permissionsList.add(readPermission);
            }
        }

        role.setPermissionsList(permissionsList);
        return ResponseEntity.ok(roleService.save(role));
    }
}
