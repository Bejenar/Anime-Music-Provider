package holo.questions.demomusicprovider.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;

@Configuration
public class HttpConfig {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder,
                                     final HttpMessageConverter customMessageConverter,
                                     @Value("${rest.client.connect.timeout}") final Duration connectTimeout,
                                     @Value("${rest.client.read.timeout}") final Duration readTimeout) {
        return builder
            .setConnectTimeout(connectTimeout)
            .setReadTimeout(readTimeout)
            .messageConverters(customMessageConverter)
            .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter customMessageConverter(final ObjectMapper customObjectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        converter.setObjectMapper(customObjectMapper);
        return converter;
    }

    @Bean
    public ObjectMapper customObjectMapper(final Jackson2ObjectMapperBuilder builder) {
        builder.featuresToEnable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        return builder.createXmlMapper(false).build();
    }

}
