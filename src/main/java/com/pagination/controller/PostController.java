package com.pagination.controller;

import com.pagination.payload.PostDTO;
import com.pagination.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
     @Autowired
     private PostService postService;
     @Autowired
     private PasswordEncoder passwordEncoder;

     @Value("${spring.datasource.url}")
     private String url;
     Logger logger = LoggerFactory.getLogger(PostController.class);

     @GetMapping("/get")
     public String getUrl() {

          return url;
     }

     @GetMapping("/message")
     public String getMessage() {
          logger.info("[getMessage] infoLog");
          logger.warn("[getMessage] warnLog");
          logger.error("[getMessage] infoErrorLog");
          logger.debug("[getMessage] DebugLog");
          logger.trace("[getMessage] tracerLog");

          return "log message";
     }

     @PostMapping

     public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
          String encode = passwordEncoder.encode(postDTO.getPasswword());
          postDTO.setPasswword(encode);
          PostDTO post = postService.createPost(postDTO);


          return new ResponseEntity<>(post, HttpStatus.CREATED);
     }

     //http://localhost:3306/api/posts?pageNo=0&pageSize=10&sortBy=title&sortDir=asc
     @GetMapping

     public ResponseEntity<Page<PostDTO>> getAllPost(@PageableDefault(sort = ("id"), size = 10) Pageable pageable) {
          Page<PostDTO> list = postService.getAllPost(pageable);
          return new ResponseEntity<>(list, HttpStatus.OK);
     }

     @GetMapping("/{id}")

     public ResponseEntity<PostDTO> getById(@PathVariable("id") long id) {
          PostDTO dto = postService.getById(id);
          return new ResponseEntity<>(dto, HttpStatus.OK);
     }

     @PutMapping("/{id}")
     public ResponseEntity<PostDTO> updateById(@PathVariable("id") long id, @RequestBody PostDTO postDTO) {
          PostDTO update = postService.updateById(id, postDTO);
          return ResponseEntity.ok(update);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<String> delete(@PathVariable("id") long id) {
          postService.deleteById(id);

          return new ResponseEntity<>("deleted!!", HttpStatus.OK);
     }

     @PostMapping("/login")
     public ResponseEntity<PostDTO> login(@RequestParam("email") String email, @RequestParam("passwword") String passwword) {
          PostDTO loggedInPost = postService.login(email, passwword);
          if (loggedInPost != null) {
               return ResponseEntity.ok(loggedInPost);
          } else {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
          }


     }
}