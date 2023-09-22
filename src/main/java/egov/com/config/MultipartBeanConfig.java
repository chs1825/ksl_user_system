package egov.com.config;

import egov.com.custom.CustomCommonResolver;
import egov.com.custom.CustomMultipartResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.annotation.MultipartConfig;

@Configuration
@Slf4j
public class MultipartBeanConfig {
    @Bean
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver commonsMultipartResolver = new CustomCommonResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }


}
