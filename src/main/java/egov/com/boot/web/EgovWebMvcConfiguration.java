package egov.com.boot.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
    일단 여기서는 인코딩 설정 진행
    mvc 관련 설정이 필요한건지 미지수

* */


//@AllArgsConstructor
@Configuration
public class EgovWebMvcConfiguration implements WebMvcConfigurer {



//    @Bean
//    public Filter sessionFilter(){
//        return new SessionFilter();
//    }




}
