/*
package com.embarkx.blogapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private BlogController blogController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    */
/*private Post samplePost(String title, String content) {
        return new Post(UUID.randomUUID(), title, content, LocalDateTime.now());
    }*//*


    */
/*@Test
    void createPost_returns201_whenValid() throws Exception {
        Post created = samplePost("Valid Title", "Valid content");
        when(postService.createPost(eq("Valid Title"), eq("Valid content"))).thenReturn(created);

        mockMvc.perform(post("/api/posts")
                        .param("title", "Valid Title")
                        .param("content", "Valid content"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Valid Title"))
                .andExpect(jsonPath("$.content").value("Valid content"));
    }*//*


    @Test
    void createPost_returns400_whenServiceThrowsValidationException() throws Exception {
        when(postService.createPost(any(), any()))
                .thenThrow(new PostValidationException("Title must be between 3 and 100 characters"));

        mockMvc.perform(post("/api/posts")
                        .param("title", "x")
                        .param("content", "irrelevant"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Title must be between 3 and 100 characters"));
    }

    @Test
    void getAllPosts_returns200_withListOfPosts() throws Exception {
        List<Post> posts = List.of(samplePost("First", "First content"), samplePost("Second", "Second content"));
        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("First"))
                .andExpect(jsonPath("$[1].title").value("Second"));
    }

    @Test
    void getPost_returns200_whenFound() throws Exception {
        UUID id = UUID.randomUUID();
        Post post = samplePost("Found Title", "Found content");
        when(postService.getPostById(id)).thenReturn(post);

        mockMvc.perform(get("/api/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Found Title"));
    }

    @Test
    void getPost_returns404_whenNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(postService.getPostById(id)).thenThrow(new PostNotFoundException(id));

        mockMvc.perform(get("/api/posts/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePost_returns204_whenDeleted() throws Exception {
        UUID id = UUID.randomUUID();
        when(postService.deletePost(id)).thenReturn(samplePost("Deleted", "Deleted content"));

        mockMvc.perform(delete("/api/posts/{id}", id))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    void searchByTitle_returns200_withResults() throws Exception {
        List<Post> results = List.of(samplePost("Matching Title", "Some content"));
        when(postService.searchByTitle("Matching")).thenReturn(results);

        mockMvc.perform(get("/api/posts/search").param("title", "Matching"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Matching Title"));
    }
}
*/
