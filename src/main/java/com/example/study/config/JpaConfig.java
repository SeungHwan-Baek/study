package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration          // Configuration 선언
@EnableJpaAuditing      // JPA 감시 활성화
public class JpaConfig {
}
