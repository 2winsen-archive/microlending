package lv.vitalijs.shakels.microlending.spring.config;

import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.stubs.LoanRepositoryStub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class ApplicationContextTestConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public static LoanRepository getLoanRepository() {
		return new LoanRepositoryStub();
	}

}
