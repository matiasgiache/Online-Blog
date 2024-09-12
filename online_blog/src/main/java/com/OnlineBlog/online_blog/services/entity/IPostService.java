package com.OnlineBlog.online_blog.services.entity;

import com.OnlineBlog.online_blog.models.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    public Post save(Post post);
    public List<Post> findAllPostByAuthorId(Long authorId);
    public Optional<Post> findPostById(Long id);
    public Post update(Long id, Post post);
    public void delete(Long id);
}
