package pl.mosek.applausematcher;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "${app.server-url}", description = "Default Server URL")})
public class OpenApiConfiguration {

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Applause Tester Matcher API")
                        .version("1.0.0")
                        .description("Autodocumented Applause Tester Matcher API"));
    }
}
