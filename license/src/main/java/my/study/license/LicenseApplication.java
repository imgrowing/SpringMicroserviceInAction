package my.study.license;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@Slf4j
public class LicenseApplication {

	@Value("${example.property}")
	private String exampleProperty;

	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> log.warn("example.property: {}", exampleProperty);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US); // US를 기본 로케일로 설정한다.
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("utf-8");
		messageSource.setUseCodeAsDefaultMessage(true); // 메시지가 발견되지 않아도 에러를 던지지 않고, 대신 메시지 코드를 반환한다.
		messageSource.setBasenames("messages"); // 언어 프로퍼티 파일의 기본 이름을 지정한다. messages.properties
		return messageSource;
	}

}
