package com.devi.blog.springboot.web;

import com.devi.blog.springboot.domain.posts.PostsRepository;
import com.devi.blog.springboot.service.posts.PostsService;
import com.devi.blog.springboot.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    @After
    public void teatDown(){
        postsRepository.deleteAll();
    }

    @Test
    public void indexTest(){
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("Spring Boot Index Page");
    }

    @Test
    public void postsSavePageTest(){
        //when
        String body = restTemplate.getForObject("/posts/save", String.class);

        //then
        assertThat(body).contains("Spring Boot Save Page");
    }

    @Test
    public void postsUpdatePageTest(){
        //given
        String title = "title";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .author("author")
                .content("content")
                .build();
        postsRepository.save(requestDto.toEntity());
        Long id = postsRepository.findAll().get(0).getId();

        //when
        String body = restTemplate.getForObject("/posts/update/" + id, String.class);

        //then
        assertThat(body).contains("Spring Boot Update Page");
        assertThat(body).contains(title);

    }
}
