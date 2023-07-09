package com.msnishan.basicgraphql.basicgraphql.service;

import com.msnishan.basicgraphql.basicgraphql.record.Post;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    private Map<Long, Post> posts = new HashMap<>();

    public Post getById(Long id) {
        return posts.get(id);
    }

    public Collection<Post> getAllPosts() {
        return posts.values();
    }

    @PostConstruct
    public void loadPosts() {
        posts.put(1L, new Post(1L, "post 1", 1L));
        posts.put(2L, new Post(2L, "post 2", 2L));
        posts.put(3L, new Post(3L, "post 3", 3L));
        posts.put(4L, new Post(4L, "post 4", 4L));
        posts.put(5L, new Post(5L, "post 5", 1L));
        posts.put(6L, new Post(6L, "post 6", 2L));
    }
}
