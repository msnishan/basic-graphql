package com.msnishan.basicgraphql.basicgraphql.service;

import com.msnishan.basicgraphql.basicgraphql.record.Author;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorService {

    private Map<Long, Author> authors = new HashMap<>();

    public Author getAuthorById(Long id) {
        return authors.get(id);
    }


    @PostConstruct
    public void createPosts() {
        authors.put(1L, new Author(1L, "1"));
        authors.put(2L, new Author(2L, "2"));
        authors.put(3L, new Author(3L, "3"));
        authors.put(4L, new Author(4L, "4"));
        authors.put(5L, new Author(5L, "5"));
    }
}
