package lv.vitalijs.shakels.microlending.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({ "lv.vitalijs.shakels.microlending.facade",
		"lv.vitalijs.shakels.microlending.services",
		"lv.vitalijs.shakels.microlending.repositories" })
public class ApplicationContextConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
