package com.msnishan.basicgraphql.basicgraphql.record;

public record Comment(Long id, String text, Long authorId, Long postId) {
}
