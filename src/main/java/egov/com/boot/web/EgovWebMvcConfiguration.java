package egov.com.boot.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;


/*
    일단 여기서는 인코딩 설정 진행
    mvc 관련 설정이 필요한건지 미지수

* */



@Configuration
public class EgovWebMvcConfiguration implements WebMvcConfigurer {

    //인코딩 필터
    @Bean
    public FilterRegistrationBean encodingFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("UTF-8");
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("*.do");
        return registrationBean;
    }

//    @Bean
//    public Filter sessionFilter(){
//        return new SessionFilter();
//    }

    // xss 필터 추가



}
