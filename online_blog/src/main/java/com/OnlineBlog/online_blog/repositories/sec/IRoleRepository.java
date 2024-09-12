package com.OnlineBlog.online_blog.repositories.sec;

import com.OnlineBlog.online_blog.models.sec.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
