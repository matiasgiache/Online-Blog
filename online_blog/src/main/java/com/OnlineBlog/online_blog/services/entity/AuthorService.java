package com.OnlineBlog.online_blog.services.entity;

import com.OnlineBlog.online_blog.models.entity.Author;
import com.OnlineBlog.online_blog.repositories.entity.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private IAuthorRepository repository;
    @Autowired
    private IPostService postService;
    @Override
    public Author save(Author author) {
        author.setPostList(postService.findAllPostByAuthorId(author.getId()));
        return repository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Author update(Long id, Author author) {
        Optional<Author> author1 = repository.findById(id);
        if (author1.isPresent()){
            Author author2 = author1.get();
            author2.setUsername(author.getUsername());
            author2.setPostList(postService.findAllPostByAuthorId(author2.getId()));

            return this.save(author2);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
