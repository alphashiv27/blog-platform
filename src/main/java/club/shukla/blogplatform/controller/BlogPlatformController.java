package club.shukla.blogplatform.controller;

import club.shukla.blogplatform.model.BlogPost;
import club.shukla.blogplatform.model.BlogPostRequest;
import club.shukla.blogplatform.svc.IBlogPlatformSvc;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogPlatformController {
    private final IBlogPlatformSvc blogPlatformSvc;

    @PostMapping("/posts")
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPostRequest request) throws URISyntaxException {
        BlogPost post = blogPlatformSvc.createPost(request);
        return ResponseEntity.created(new URI("/posts/" + post.getId())).body(post);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long postId, @RequestBody BlogPostRequest request) {
        BlogPost post = blogPlatformSvc.updatePost(postId, request);
        return ResponseEntity.accepted().body(post);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        blogPlatformSvc.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<BlogPost> getPost(@PathVariable Long postId) {
        BlogPost blogPost = blogPlatformSvc.getPost(postId);
        return ResponseEntity.ok().body(blogPost);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<BlogPost>> getPosts(@RequestParam(required = false) String term){
        List<BlogPost> blogPosts = blogPlatformSvc.getPostsBySearchTerm(term);
        return ResponseEntity.ok().body(blogPosts);
    }
}
