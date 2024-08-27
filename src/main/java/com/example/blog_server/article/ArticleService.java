package com.example.blog_server.article;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blog_server.article.dtos.CreateArticleRequest;
import com.example.blog_server.article.dtos.UpdateArticleRequest;
import com.example.blog_server.user.UserRepository;
import com.example.blog_server.user.UserService;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository){
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Iterable<ArticleEntity> getAllArticle(){
        return articleRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug){
        var article = articleRepository.findBySlug(slug);
        if(article==null){
             throw new ArticleNotFoundException(slug);
        }
        return article;
    }
    
    public ArticleEntity createArticle(CreateArticleRequest a, Long authorId){
        var author = userRepository.findById(authorId).orElseThrow(()-> new UserService.UserNotFoundException(authorId));
        return articleRepository.save(ArticleEntity.builder()
                                    .title(a.getTitle())
                                    .slug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                                    .body(a.getBody())
                                    .subtitle(a.getSubtitle())
                                    .imageLink(a.getImageLink())
                                    .author(author)
                                    .build()
                                    );
    }


    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest a, Long userId){
        var article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
        
        if (!article.getAuthor().getId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to update this article");
        }

        if(a.getTitle()!=null){
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+","-"));

        }

        if(a.getBody() != null){
            article.setBody(a.getBody());

        }

        if(a.getSubtitle()!=null){
            article.setSubtitle(a.getSubtitle());

        }

        if(a.getImageLink()!=null){
            article.setImageLink(a.getImageLink());
        }

        return articleRepository.save(article);
        
    }


    static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article " + slug + " not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with id: " + id + " not found");
        }
    }


    public Optional<ArticleEntity> getArticleById(Long id) {
        // TODO Auto-generated method stub
        var article = articleRepository.findById(id);
        if(article==null){
            throw new ArticleNotFoundException(id);
        }
        return article;
    }

    public void deleteArticle(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new ArticleNotFoundException(id); // Custom exception if article not found
        }
    }
    

    
}
