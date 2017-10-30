package com.lick.controller.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description： 拦截器拦截配置
 * @Author: lick@hsyuntai.com
 * @Date: 2017年10月27日 14:03
 * @Copyright: 版权归hsyuntai 所有
 */
@Configuration
@ServletComponentScan(basePackages = {"com.lick"})
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{

    /*
    * @Description: 增加自定义拦截器
    * @Method:addInterceptors
    * @params:[registry]
    * @returnType:void
    * @Author:lick
    * @Date: 2017/10/27 14:07
    * @Copyright: 版权归 lick 所有
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //增加自定义配置的拦截器
//        registry.addInterceptor();
        super.addInterceptors(registry);

    }
    /* EmbeddedServletContainerCustomizer修改session的配置，模拟http，可以被dispatcher处理 */

    /**
     * @Description: 自定义的异常处理界面 ,需要配合tomcat
     * @Method:containerCustomizer
     * @params:[]
     * @returnType:org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
     * @Author:lick
     * @Date: 2017/10/27 15:31
     * @Copyright: 版权归lick所有
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
                configurableEmbeddedServletContainer.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }

    /**
     * @Description: 增加session的过滤链
     * @Method:sessionFilterRegistrationBean
     * @params:[]
     * @returnType:org.springframework.boot.web.servlet.FilterRegistrationBean
     * @Author:lick
     * @Date: 2017/10/27 14:57
     * @Copyright: 版权归 lick 所有
     */
//    @Bean
//    public FilterRegistrationBean sessionFilterRegistrationBean(){
//        FilterRegistrationBean sessionFilterRegistrationBean = new FilterRegistrationBean();
//        HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();
//        registrationBean.setFilter(httpBasicFilter);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/user/getuser");
//        registrationBean.setUrlPatterns(urlPatterns);
//        return sessionFilterRegistrationBean;
//    }
}
