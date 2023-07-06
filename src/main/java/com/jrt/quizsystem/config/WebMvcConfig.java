package com.jrt.quizsystem.config;

import com.jrt.quizsystem.filter.AdminFilter;
import com.jrt.quizsystem.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    @Bean
//    public FilterRegistrationBean<LoginFilter> filterFilterRegistrationBean() {
//        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
//        // we can also use autowired, but we need to include @ComponentScan above Configuration Class
//        LoginFilter loginFilter = new LoginFilter();
//        registrationBean.setFilter(loginFilter);
//        //set the precedence, higher than adminfilter
//        registrationBean.setOrder(0);
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean<AdminFilter> filterFilterRegistrationBean() {
//        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
//        AdminFilter adminFilter = new AdminFilter();
//        registrationBean.setFilter(adminFilter);
//        //set precedence
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
}
