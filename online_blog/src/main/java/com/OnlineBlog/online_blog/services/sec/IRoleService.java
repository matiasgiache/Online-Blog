package com.OnlineBlog.online_blog.services.sec;

import com.OnlineBlog.online_blog.models.sec.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    public List<Role> findAll();
    public Optional<Role> findById(Long id);
    public Role save(Role role);
    public void deleteById(Long id);
    public void update(Role role);
}
