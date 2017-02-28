package com.mine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



@Configuration
@EnableTransactionManagement
@Import({WebConfig.class, SwaggerDocumentationConfig.class, })
@ComponentScan({"com.mine.*"})
public class AppConfig extends RepositoryRestConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.setBasePath("/api");
        repositoryRestConfiguration.setDefaultMediaType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }
}
