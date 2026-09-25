package com.embarkx.blogapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class BlogController {

    private final PostService postService;

    @Value("${blog.content.max-length}")
    private int maxContentLength;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title, @RequestParam String content) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(title, content));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(postService.searchByTitle(title));
    }

    @PostMapping("/validate")
    public String validateContent(@RequestParam String content) {
        if (content.length() > maxContentLength) {
            return "Too long";
        }
        return "OK";
    }

    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestParam UUID id, @RequestParam String title, @RequestParam String content) {
        return ResponseEntity.ok(postService.updatePost(id, title, content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public String getTotalWordCount() {
        List<Integer> wordCounts = List.of(100, 200, 300);
        int total = 0;
        for (int count : wordCounts) {
            total += count;
        }
        return "Total words: " + total;
    }
}
