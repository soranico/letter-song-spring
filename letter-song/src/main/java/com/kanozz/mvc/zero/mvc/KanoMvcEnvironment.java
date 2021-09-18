package com.kanozz.mvc.zero.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableWebMvc
@ComponentScan("com.kanozz.mvc.zero.mvc")
public class KanoMvcEnvironment implements WebMvcConfigurer {
}
