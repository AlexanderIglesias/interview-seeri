package co.com.seeri.products.api.chat.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Chat",
                version = "1.0.0",
                description = "API To Seeri challenge"

        )
)
public class SwaggerConfig {

}


