package com.pagination.service;

import com.pagination.entities.Post;
import com.pagination.payload.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    
    PostDTO createPost(PostDTO postDTO);

    Page<PostDTO> getAllPost(Pageable pageable);

    PostDTO getById(long id);

    PostDTO updateById(long id, PostDTO postDTO);

    void deleteById(long id);

    PostDTO login(String email,String passwword);
}
