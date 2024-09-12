package com.OnlineBlog.online_blog.services.entity;

import com.OnlineBlog.online_blog.models.entity.Post;
import com.OnlineBlog.online_blog.repositories.entity.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository repository;

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public List<Post> findAllPostByAuthorId(Long authorId) {
        return repository.findAllPostByAuthorId(authorId);
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Post update(Long id, Post post) {

        Optional<Post> post1 = repository.findById(id);
        if (post1.isPresent()){
            Post postToUp = post1.get();
            postToUp.setAuthor(post.getAuthor());
            postToUp.setDate(post.getDate());
            postToUp.setContent(post.getContent());

            return this.save(postToUp);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
