package socket.rocket;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration {

	@Bean
	public HttpMessageConverters customConverters() {
		return ObjectMapping.httpMessageConverters();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return ObjectMapping.jsonBuilder();
	}

}