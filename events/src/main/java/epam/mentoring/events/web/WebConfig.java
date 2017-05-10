package epam.mentoring.events.web;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"epam.mentoring.events", "epam.mentoring.security"})
public class WebConfig {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.setPort(8082);
        };
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setPersistenceUnitName("verification");
        return emf;
    }


}