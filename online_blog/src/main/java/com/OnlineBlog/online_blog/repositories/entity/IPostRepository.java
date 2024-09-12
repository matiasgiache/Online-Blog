package com.OnlineBlog.online_blog.repositories.entity;

import com.OnlineBlog.online_blog.models.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE author.id = :authorId")
    List<Post> findAllPostByAuthorId(@Param("authorId") Long authorId);
}
