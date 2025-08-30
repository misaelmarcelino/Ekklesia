package br.com.mimatech.Ekklesia3d.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpanApiConfig {
    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI().info(
            new Info()
                    .title("Ekklesia API")
                    .description("API de Gestão Eclesiástica - Gerencia o seu ministério com o melhor recurso!")
                    .version("v1.0.0")
                    .contact(new Contact().name("Misael Marcelino").email("suporte@ekklesia3d.app"))
        );
    }
}
