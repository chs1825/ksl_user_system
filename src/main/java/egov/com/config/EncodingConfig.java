package egov.com.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
@Configuration
public class EncodingConfig {

    //인코딩 필터
    @Bean
    public FilterRegistrationBean encodingFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("UTF-8");
        registrationBean.setFilter(filter);
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("*.do");
        return registrationBean;
    }

}
