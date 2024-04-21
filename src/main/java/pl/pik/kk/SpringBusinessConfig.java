package pl.pik.kk;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"pl.pik.kk"}, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "pl\\.pik\\.kk\\.web\\..*")})
@ImportResource({"classpath:/applicationContext*.xml"})
public class SpringBusinessConfig {

    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint) {
        Class<?> containingClass = injectionPoint.getField().getDeclaringClass();
        return LoggerFactory.getLogger(containingClass);
    }

}
