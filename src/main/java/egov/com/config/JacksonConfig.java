package egov.com.config;

import com.fasterxml.jackson.core.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//@Configuration
public class JacksonConfig implements Jackson2ObjectMapperBuilderCustomizer {
    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES).build();
    }
}
