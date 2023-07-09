package com.msnishan.basicgraphql.basicgraphql;

import com.msnishan.basicgraphql.basicgraphql.record.Comment;
import com.msnishan.basicgraphql.basicgraphql.record.Post;
import com.msnishan.basicgraphql.basicgraphql.service.AuthorService;
import com.msnishan.basicgraphql.basicgraphql.service.CommentService;
import com.msnishan.basicgraphql.basicgraphql.service.PostService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.function.UnaryOperator;

@SpringBootApplication
public class BasicGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicGraphqlApplication.class, args);
	}

	@Bean
	RuntimeWiringConfigurer runtimeWiringConfigurer(PostService postService, AuthorService authorService, CommentService commentService) {
		return builder -> {

			builder.type("Comment", builder1 -> builder1
					.dataFetcher("author", environment -> authorService.getAuthorById( ((Comment) environment.getSource()).authorId())));

			builder.type("Post", wiring -> wiring
					.dataFetcher("author", environment -> authorService.getAuthorById(((Post) environment.getSource()).authorId()))
					.dataFetcher("comments", environment -> commentService.getCommentsByPost(environment.getSource()))
			);

			builder.type("Query", builder1 -> builder1
					.dataFetcher("postById", environment -> postService.getById(Long.parseLong(environment.getArgument("id"))))
					.dataFetcher("posts", environment -> postService.getAllPosts()));
		};
	}

}
