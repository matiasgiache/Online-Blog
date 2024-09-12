package com.OnlineBlog.online_blog.services.sec;

import com.OnlineBlog.online_blog.models.sec.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    public List<Permission> findAll();
    public Optional<Permission> findById(Long id);
    public Permission save(Permission permission);
    public void deleteById(Long id);
    public void update(Permission permission);
}
