package com.example.blog_server.common;

import org.springframework.stereotype.Component;

import com.example.blog_server.article.ArticleService;
import com.example.blog_server.article.dtos.CreateArticleRequest;
import com.example.blog_server.comments.CommentService;
import com.example.blog_server.comments.dtos.CreateCommentRequest;
import com.example.blog_server.user.UserService;
import com.example.blog_server.user.dtos.CreateUserRequest;

@Component
public class SeedData {
    UserService userService;
    ArticleService articleService;
    CommentService commentService;

    public SeedData(UserService userService, ArticleService articleService, CommentService commentService) {
        System.out.println("########################Seeding Data#########################");
        this.userService = userService;
        this.articleService = articleService;
        this.commentService = commentService;

        createUsers();
        createArticles();
        createComments();
    }

    public void createUsers(){
        userService.createUser(CreateUserRequest.builder().username("siddharth").password("Sid@1234").email("siddharth@gmail.com").build());
        userService.createUser(CreateUserRequest.builder().username("siddharth1").password("Sid@1234").email("siddharth1@gmail.com").build());
        userService.createUser(CreateUserRequest.builder().username("siddharth2").password("Sid@1234").email("siddharth2@gmail.com").build());
        userService.createUser(CreateUserRequest.builder().username("siddharth3").password("Sid@1234").email("siddharth3@gmail.com").build());
    }


    public void createArticles(){

        var user = userService.getUser("siddharth");
        var user1 = userService.getUser("siddharth1");
        var user2 = userService.getUser("siddharth2");
        var user3 = userService.getUser("siddharth3");
        articleService.createArticle(CreateArticleRequest.builder().title("Title1").body("<ul>\n" + //
                        "        <li>The Moon is Earth's only natural satellite and the fifth-largest moon in the Solar System.</li>\n" + //
                        "        <li>It is about 384,400 km (238,855 miles) away from Earth.</li>\n" + //
                        "        <li>The Moon has a diameter of 3,474 km (2,159 miles), about one-quarter the size of Earth.</li>\n" + //
                        "        <li>It takes approximately 27.3 days for the Moon to orbit Earth, known as a sidereal month.</li>\n" + //
                        "        <li>The Moon's surface is covered in craters, valleys, and flat plains called maria.</li>\n" + //
                        "        <li>The far side of the Moon, often mistakenly called the \"dark side,\" is never visible from Earth.</li>\n" + //
                        "        <li>The Moon has a very thin atmosphere called an exosphere, which does not support life.</li>\n" + //
                        "        <li>The Moon influences Earth's tides due to its gravitational pull.</li>\n" + //
                        "        <li>It experiences extreme temperature variations, ranging from -173°C (-280°F) at night to 127°C (260°F) during the day.</li>\n" + //
                        "        <li>The Moon has no water in liquid form, but water ice has been found in permanently shadowed craters.</li>\n" + //
                        "        <li>The Moon's gravitational pull is about one-sixth that of Earth's, making objects weigh less.</li>\n" + //
                        "        <li>During a lunar eclipse, the Earth comes between the Sun and the Moon, casting a shadow on the Moon.</li>\n" + //
                        "        <li>The Moon's phases, such as new moon, full moon, and crescent, are caused by its position relative to Earth and the Sun.</li>\n" + //
                        "        <li>Neil Armstrong and Buzz Aldrin were the first humans to land on the Moon on July 20, 1969, during NASA's Apollo 11 mission.</li>\n" + //
                        "        <li>The Moon is slowly drifting away from Earth at a rate of about 3.8 cm (1.5 inches) per year.</li>\n" + //
                        "        <li>The Moon has no magnetic field, unlike Earth.</li>\n" + //
                        "        <li>It is believed that the Moon was formed about 4.5 billion years ago, possibly from debris left after a collision between Earth and a Mars-sized object.</li>\n" + //
                        "        <li>The largest crater on the Moon is the South Pole-Aitken Basin, which is about 2,500 km (1,550 miles) in diameter.</li>\n" + //
                        "        <li>The Moon's surface is covered in fine dust and rocky debris known as regolith.</li>\n" + //
                        "        <li>Future space missions, like NASA's Artemis program, aim to return humans to the Moon and establish a sustainable presence.</li>\n" + //
                        "    </ul>").subtitle("Subtitle1").imageLink("https://wallpapercave.com/wp/GpUHmC4.jpg").build(), user.getId());
        articleService.createArticle(CreateArticleRequest.builder().title("Title2").body("Body2").subtitle("Subtitle2").imageLink("https://wallpapercave.com/wp/Dvslduk.jpg").build(), user1.getId());
        articleService.createArticle(CreateArticleRequest.builder().title("Title3").body("Body3").subtitle("Subtitle3").imageLink("https://wallpapercave.com/uwp/uwp4211601.png").build(), user2.getId());
        articleService.createArticle(CreateArticleRequest.builder().title("Title4").body("Body4").subtitle("Subtitle4").imageLink("https://wallpapercave.com/wp/wp4096172.jpg").build(), user3.getId());
        articleService.createArticle(CreateArticleRequest.builder().title("Title5").body("Body5").subtitle("Subtitle5").imageLink("https://images2.alphacoders.com/602/thumb-1920-602604.jpg").build(), user2.getId());
        articleService.createArticle(CreateArticleRequest.builder().title("Title6").body("Body6").subtitle("Subtitle6").imageLink("https://images6.alphacoders.com/133/thumb-1920-1339623.png").build(), user.getId());
    }


    public void createComments(){

        var user = userService.getUser("siddharth");
        var user1 = userService.getUser("siddharth1");
        var user2 = userService.getUser("siddharth2");
        var user3 = userService.getUser("siddharth3");
        var article = articleService.getArticleBySlug("title1");
        var article1 = articleService.getArticleBySlug("title2");
        var article2 = articleService.getArticleBySlug("title3");  
        var article3 = articleService.getArticleBySlug("title4");
        commentService.createComment(CreateCommentRequest.builder().body("body1").title("title1").build(), user.getId(), article.getId());
        commentService.createComment(CreateCommentRequest.builder().body("body2").title("title2").build(), user1.getId(), article1.getId());
        commentService.createComment(CreateCommentRequest.builder().body("body3").title("title3").build(), user2.getId(), article2.getId());
        commentService.createComment(CreateCommentRequest.builder().body("body4").title("title4").build(), user3.getId(), article3.getId());

    }
       
}
