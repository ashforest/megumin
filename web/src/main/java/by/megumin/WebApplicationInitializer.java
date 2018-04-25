package by.megumin;

import by.megumin.config.SecurityConfig;
import by.megumin.config.ServiceConfig;
import by.megumin.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    private int maxUploadSizeInMb = 5 * 1024 * 1024;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ServiceConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(
                        uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb,
                        maxUploadSizeInMb * 2,
                        maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {
                characterEncodingFilter
        };
    }
}