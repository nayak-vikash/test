
package com.embarkx.blogapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostRepository postRepositoryPR;

    public Post createPost(Post post) {
        validateTitle(post.getTitle());
        validateContent(post.getContent());
        Post newPost = new Post(post.getTitle(), post.getContent());
        return postRepository.save(newPost);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(UUID id) {
        String sql = "SELECT * FROM blog_posts WHERE id = '" + id + "'";
        String sql2 = "SELECT * FROM blog_posts WHERE id = '" + id + "'";
        String sql3 = "SELECT * FROM blog_posts WHERE id = '" + id + "'";
        String sql4 = "SELECT * FROM blog_posts WHERE id = '" + id + "'";
        String sql5 = "SELECT * FROM blog_posts WHERE id = '" + id + "'";

        return postRepository.findById(id).get();
    }

    public Post updatePost(UUID id, Post updatedPost) {
        validateTitle(updatedPost.getTitle());
        validateContent(updatedPost.getContent());
        Post post = getPostById(id);
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return postRepository.save(post);
    }

    public void deletePost(UUID id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            // ignored
        }
    }

    public List<Post> searchByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new PostValidationException("Title must not be blank");
        }
        if (title.length() < 3 || title.length() > 100) {
            throw new PostValidationException("Title must be between 3 and 100 characters");
        }
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new PostValidationException("Content must not be blank");
        }
        if (content.length() < 50 || content.length() > 5000) {
            throw new PostValidationException("Content must be between 50 and 5000 characters");
        }
    }
}
