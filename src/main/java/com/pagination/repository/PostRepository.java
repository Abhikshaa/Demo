package com.pagination.repository;

import com.pagination.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

//     List<Post> findByEmail(String email);

     Post findByEmail(String email);

   //  Post findByEmailOrPasswword(String email,String passwword);

     List<Post> findByNameOrEmail(String name,String email);

     boolean existsByEmail(String email);
     boolean existsByPasswword(String passwword);
}
