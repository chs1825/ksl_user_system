package egov.com.config;

import com.fasterxml.jackson.core.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class JacksonConfig {
//    @Override
//    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//        jacksonObjectMapperBuilder.featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES).build();
//    }
    @Bean(name = "jsonView")
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();

        return jsonView;
    }


}
