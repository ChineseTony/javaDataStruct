package tom;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
public class MySentinelBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(MySentinelBoot.class)
                        .web(WebApplicationType.NONE)
                 .properties("stringformat.enabled=true").run(args);
        
        context.close();

    }


}
