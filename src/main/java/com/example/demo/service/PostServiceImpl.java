package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// PostServiceImpl.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    
    private final PostRepository postRepository;
    
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public List<Post> postList() {
        return postRepository.findAll();
    } 
    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
    
        // 각 포스트의 이미지를 Base64로 변환
        for (Post post : posts) {
            if (post != null && post.getImageUrl() != null) {
                // 문제가 될 수 있는 부분: byte[]를 Base64로 인코딩하는 부분
                String base64Image = Base64.getEncoder().encodeToString(post.getImageUrl());
                post.setImageBase64(base64Image);
            }
        }
    
        return posts;
    }

    @Override
    public void write(Post post) {
        postRepository.save(post);
    }
    
}


