package com.refactorizando.hexagonalarchitecture.infrastructure.config.spring;


import com.refactorizando.hexagonalarchitecture.application.repository.CharacterRepository;
import com.refactorizando.hexagonalarchitecture.application.repository.MovieRepository;
import com.refactorizando.hexagonalarchitecture.application.service.CharacterService;
import com.refactorizando.hexagonalarchitecture.application.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.refactorizando.hexagonalarchitecture.application.repository.UserRepository;
import com.refactorizando.hexagonalarchitecture.application.service.UserService;

@Configuration
public class SpringBootServiceConfig {


  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
  }


  @Bean
  public MovieService movieService(MovieRepository movieRepository) {
    return new MovieService(movieRepository);

  }  @Bean
  public CharacterService characterService(CharacterRepository characterRepository) {
    return new CharacterService(characterRepository);
  }

}