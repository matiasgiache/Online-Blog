package com.OnlineBlog.online_blog.controllers.entity;

import com.OnlineBlog.online_blog.models.entity.Post;
import com.OnlineBlog.online_blog.services.entity.IAuthorService;
import com.OnlineBlog.online_blog.services.entity.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IAuthorService authorService;

    @GetMapping("/getAll/{authorId}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Post>> findAllPostsByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(postService.findAllPostByAuthorId(authorId));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findPostById(id).orElse(null));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        Long authorId = post.getAuthor().getId();
        post.setAuthor(authorService.findById(authorId).orElse(null));

        return ResponseEntity.ok(postService.save(post));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {

        Long authorId = post.getAuthor().getId();
        post.setAuthor(authorService.findById(authorId).orElse(null));

        return ResponseEntity.ok(postService.update(id, post));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        postService.delete(id);

        return ResponseEntity.ok("Post deleted.");
    }

}
