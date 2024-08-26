package com.example.blog_server.article;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog_server.article.dtos.CreateArticleRequest;
import com.example.blog_server.user.UserEntity;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    String getArticle() {
        return "get All articles";
    }

    @GetMapping("/{id}")
    String getArticleById(@PathVariable("id") String id) {
        return "get article with id:" + id;
    }

    @PostMapping("")
    public ResponseEntity<?> createArticle(@AuthenticationPrincipal UserEntity user,
            @RequestBody CreateArticleRequest article) {
        try {
            if (article.getTitle() != null && article.getBody() != null) {
                // Assuming articleService.createArticle returns the created ArticleEntity
                ArticleEntity createdArticle = articleService.createArticle(article, user.getId());
                // Assuming createArticle returns the ID or URI of the created article
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdArticle.getId())
                        .toUri();
                return ResponseEntity.created(location).body(createdArticle);
            } else {
                // Return a 400 Bad Request if the title or body is missing
                return ResponseEntity.badRequest().body("Title and body must not be null");
            }
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the article");
        }
    }

}
