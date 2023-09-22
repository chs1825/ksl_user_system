package egov.com.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import egov.com.filter.xss.BodyXssFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@Slf4j
public class XssConfig implements WebMvcConfigurer {

    //Filter에 포함되는 URL 주소
    private static final String[] INCLUDE_PATHS = {
            "/test/*",
            "/test2/*"
    };
    // luxy-xss 필터
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new XssEscapeServletFilter());
        filterRegistration.setOrder(2);
//        filterRegistration.setUrlPatterns(Collections.singletonList("*.do"));
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    // RequsetBody 필터
    // lucy 필터와 url 규칙이 겹치면 루시필터는 적용이 안되니 url 규칙이 필요하다.
    @Bean
    public FilterRegistrationBean xssRequestBodyFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new BodyXssFilter());

        registrationBean.setOrder(3); //필터 여러개 적용 시 순번
//        registrationBean.setOrder(Integer.MIN_VALUE); //필터 여러개 적용 시 순번
//        registrationBean.addUrlPatterns("/*"); //전체 URL 포함
//        registrationBean.addUrlPatterns("/submit-body"); //특정 URL 포함
//        registrationBean.setUrlPatterns(Arrays.asList(INCLUDE_PATHS)); //여러 특정 URL 포함
        registrationBean.setUrlPatterns(Arrays.asList("/submit-body", "/test2/*"));

        return registrationBean;

    }



}
