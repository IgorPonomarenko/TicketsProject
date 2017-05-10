package epam.mentoring.ticketsstore.web;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Created by Igor_Ponomarenko on 07.04.2017.
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"epam.mentoring.ticketsstore", "epam.mentoring.security"})
public class WebConfig {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.setPort(8083);
        };
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setPersistenceUnitName("store");
        return emf;
    }

}
