package com.pagination.repository;

import com.pagination.entities.Post;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostRepositoryTest {

     @Autowired
     private PostRepository postRepository;

     @Test
     @Disabled
     void findByEmail() {

          Post post = new Post
                  (4,"Vani kapoor","vani@gmail.com"
                          ,"$2a$10$03NcclblP4t0L4gXHYVtuug51n8q7MniwxyQ1wImoPz2EgIlpd.V6",
                          880655434);

          String expected="vani@gmail.com";

         // List<Post> actual = postRepository.findByEmail("vani@gmail.com");

        //  assertThat(actual).isEqualTo(expected);
     }

     @Test
     void existsByEmail() {
          boolean actualResult = postRepository.existsByEmail("vani@gmail.com");

          assertThat(actualResult).isTrue();
     }

     @Test
     void existsByPasswword() {
          boolean testing = postRepository.existsByPasswword
                  ("$2a$10$03NcclblP4t0L4gXHYVtuug51n8q7MniwxyQ1wImoPz2EgIlpd.V6");
          assertThat(testing).isTrue();
     }
}