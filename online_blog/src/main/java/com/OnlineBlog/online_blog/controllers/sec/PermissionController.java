package com.OnlineBlog.online_blog.controllers.sec;

import com.OnlineBlog.online_blog.models.sec.Permission;
import com.OnlineBlog.online_blog.services.sec.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/getAll")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Permission>> getAllPermissions(){
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/get/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
        return ResponseEntity.ok(permissionService.save(permission));
    }
}
