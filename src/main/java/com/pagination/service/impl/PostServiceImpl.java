package com.pagination.service.impl;

import com.pagination.entities.Post;
import com.pagination.exception.ResourceNotFoundException;
import com.pagination.payload.PostDTO;
import com.pagination.repository.PostRepository;
import com.pagination.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post =mapToEntity(postDTO);
        String encode = passwordEncoder.encode(post.getPasswword());
      postDTO.setPasswword(encode);
        Post save = postRepository.save(post);
        PostDTO dto = mapToDto(save);

        return dto;
    }

    @Override
    public Page<PostDTO> getAllPost(Pageable pageable) {


        Page<Post> page = postRepository.findAll(pageable);//find all the content from database
        //pageNo,PageSize,sort all recorde in asc &desc
        List<PostDTO> collect = page.stream().map(this::mapToDto).collect(Collectors.toList());// show me list of page


        return new PageImpl<>(collect,pageable, page.getTotalElements());//return into DTO form
    }

    @Override
    public PostDTO getById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", id));

        return mapToDto(post);
    }

    @Override
    public PostDTO updateById(long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", id));
         post.setName(postDTO.getName());
         post.setEmail(postDTO.getEmail());
         post.setMobile(postDTO.getMobile());

         if(postDTO !=null){
             String encode = passwordEncoder.encode(post.getPasswword());
             postDTO.setPasswword(encode);
         }
        Post save = postRepository.save(post);
        return mapToDto(save);
    }

    @Override
    public void deleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", id));
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO login(String email, String passwword) {
        Post post = postRepository.findByEmail(email);
        if (post == null || !passwordEncoder.matches(passwword, post.getPasswword())) {

            return null;
        }
        return mapToDto(post);
    }

    PostDTO mapToDto(Post post){
        PostDTO dto = mapper.map(post,PostDTO.class );
        return dto;
    }

    Post mapToEntity(PostDTO dto){
        Post post = mapper.map(dto,Post.class);
        return post;
    }

}
