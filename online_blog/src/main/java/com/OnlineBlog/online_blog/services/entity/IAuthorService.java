package com.OnlineBlog.online_blog.services.entity;

import com.OnlineBlog.online_blog.models.entity.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {

    public Author save(Author author);
    public List<Author> findAll();
    public Optional<Author> findById(Long id);
    public Author update(Long id, Author author);
    public void delete(Long id);
}
