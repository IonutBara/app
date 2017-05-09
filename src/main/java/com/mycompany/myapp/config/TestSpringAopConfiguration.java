package com.mycompany.myapp.config;

import com.mycompany.myapp.aop.logging.TestSpringAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

/**
 * Created by ibara on 5/9/2017.
 */
@Configuration
@EnableAspectJAutoProxy
public class TestSpringAopConfiguration {

    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public TestSpringAop altAspect() {
        return new TestSpringAop();
    }
}
