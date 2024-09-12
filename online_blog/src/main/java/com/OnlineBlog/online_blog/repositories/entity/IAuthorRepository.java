package com.OnlineBlog.online_blog.repositories.entity;

import com.OnlineBlog.online_blog.models.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
}
