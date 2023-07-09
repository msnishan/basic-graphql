package com.msnishan.basicgraphql.basicgraphql.service;

import com.msnishan.basicgraphql.basicgraphql.record.Comment;
import com.msnishan.basicgraphql.basicgraphql.record.Post;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private Map<Long, Comment> comments = new HashMap<>();

    public Comment getCommentById(Long id) {
        return comments.get(id);
    }

    public Collection<Comment> getCommentsByPost(Post post) {
        return comments.values().stream()
                .filter(p -> p.postId().equals(post.id()))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void loadComments() {
        comments.put(1L, new Comment(1L, "comment for post 1 by author 1", 1L, 1L));
        comments.put(2L, new Comment(2L, "comment for post 1 by author 2", 2L, 1L));
        comments.put(3L, new Comment(3L, "comment for post 2 by author 3", 3L, 2L));
        comments.put(4L, new Comment(4L, "comment for post 3 by author 1", 1L, 3L));
    }
}
