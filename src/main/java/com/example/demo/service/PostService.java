package com.example.demo.service;

import com.example.demo.entity.Post;

import java.util.List;
import com.example.demo.service.PostServiceImpl;
import com.example.demo.repository.PostRepository;
public interface PostService {
    List<Post> getAllPosts();

    void write(Post post);

    List<Post> postList();
}