package com.pagination;
import com.pagination.entities.Post;
import com.pagination.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class PaginationApplication implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();

	}

	public static void main(String[] args) {
		SpringApplication.run(PaginationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


     //		List<Post> email = postRepository.findByEmail("tanisha@gmail.com");
     //		email.forEach(l1-> System.out.println(l1));

		List<Post> posts = postRepository.findByNameOrEmail("Vani kapoor", "vani@gmail.com");

		posts.forEach(l1->System.out.println(l1));

	}
}
