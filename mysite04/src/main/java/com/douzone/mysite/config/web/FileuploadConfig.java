package com.douzone.mysite.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileuploadConfig implements WebMvcConfigurer {

	/* Multipart Resolver */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		multipartResolver.setMaxUploadSize(52428800);
		multipartResolver.setMaxInMemorySize(52428800);
		multipartResolver.setDefaultEncoding("utf-8");
		
		return multipartResolver;
	}

	
	/* url-resource mapping : images로 들어오는 경로를 실제 경로로 연결(가상 URL)  */
	/* /images/202302071405.png -> Users/yoon/fileupload-files/202302071405.png 이렇게 매핑되게 설정하고 코딩해야함 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/assets/upload-images/**")
			.addResourceLocations("file:/Users/yoon/mysite-uploads/");
	}
}
