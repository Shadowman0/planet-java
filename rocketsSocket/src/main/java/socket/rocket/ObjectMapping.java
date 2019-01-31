package socket.rocket;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class ObjectMapping {

	public static HttpMessageConverters httpMessageConverters() {
		return new HttpMessageConverters(new MappingJackson2HttpMessageConverter(jsonBuilder()));
	}

	public static ObjectMapper jsonBuilder() {
		return createJacksonBuilder().build();
	}

	public static Jackson2ObjectMapperBuilder createJacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.timeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Berlin")));
		builder.modules(javaTimeModule(), new Jdk8Module(), nullToEmptyAndTrimStrings());
		return builder;
	}

	private static JavaTimeModule javaTimeModule() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		return javaTimeModule;
	}

	private static SimpleModule nullToEmptyAndTrimStrings() {
		SimpleModule trimStringsModule = new SimpleModule();
		trimStringsModule.addDeserializer(String.class, new NullToEmptyAndTrimDeserializer());
		return trimStringsModule;
	}

	private static class NullToEmptyAndTrimDeserializer extends StdScalarDeserializer<String> {
		private static final long serialVersionUID = 1L;

		private NullToEmptyAndTrimDeserializer() {
			super(String.class);
		}

		@Override
		public String deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
			return StringUtils.trimToEmpty(parser.getValueAsString());
		}

		@Override
		@Deprecated
		public String getNullValue() {
			return "";
		}
	}
}