package com.sistema.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("publicacionMapper")
    public ModelMapper publicacionMapper(){
        return new ModelMapper();
    }

    @Bean("comentarioMapper")
    public ModelMapper comentarioMapper(){
        return new ModelMapper();
    }
}
