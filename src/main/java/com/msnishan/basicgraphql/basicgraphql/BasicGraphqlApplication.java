package com.msnishan.basicgraphql.basicgraphql;

import com.msnishan.basicgraphql.basicgraphql.record.Comment;
import com.msnishan.basicgraphql.basicgraphql.record.Post;
import com.msnishan.basicgraphql.basicgraphql.service.AuthorService;
import com.msnishan.basicgraphql.basicgraphql.service.CommentService;
import com.msnishan.basicgraphql.basicgraphql.service.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@SpringBootApplication
public class BasicGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicGraphqlApplication.class, args);
	}

	@Bean
	RuntimeWiringConfigurer runtimeWiringConfigurer(PostService postService, AuthorService authorService, CommentService commentService) {
		return builder -> {

			//========= implemented using basic graphql-java engine API's============
			builder.type("Comment", builder1 -> builder1
					.dataFetcher("author", environment -> authorService.getAuthorById( ((Comment) environment.getSource()).authorId())));

			builder.type("Post", wiring -> wiring
					.dataFetcher("author", environment -> authorService.getAuthorById(((Post) environment.getSource()).authorId()))
					.dataFetcher("comments", environment -> commentService.getCommentsByPost(environment.getSource()))
			);

			builder.type("Query", builder1 -> builder1
					.dataFetcher("postById", environment -> postService.getById(Long.parseLong(environment.getArgument("id"))))
					.dataFetcher("posts", environment -> postService.getAllPosts()));
			//========= implemented using basic graphql-java engine API's============
		};
	}

}
