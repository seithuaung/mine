package com.mine.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



/**
 * Created by seithuaung on 23/2/17.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebInitializer.class);


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    	registration.setInitParameter("dispatchOptionRequest", "true");
    	registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    	registration.setAsyncSupported(true);
    	
    }
    
    @Override
    protected WebApplicationContext createRootApplicationContext() {
    	WebApplicationContext context = super.createRootApplicationContext();
    	ConfigurableEnvironment env = (ConfigurableEnvironment) context.getEnvironment();
    	try {
			env.getPropertySources().addFirst(new ResourcePropertySource(new ClassPathResource("env.properties")));
			LOGGER.debug("env.properties has loaded from classpath.");
		} catch (IOException e) {
			LOGGER.debug("env.properties NOT FOUND in classpath, trying to load env parameter from system property ...	");
		}
    	
    	env.setActiveProfiles(env.getRequiredProperty("env"));
		LOGGER.debug("Active profile has been set to {}", env.getRequiredProperty("env"));
		
		return context;
    }
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
    @Override
    protected Filter[] getServletFilters(){
    	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    	characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
    	characterEncodingFilter.setForceEncoding(true);
    	return new Filter[] {characterEncodingFilter};
    }
}
