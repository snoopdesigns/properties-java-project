package org.snoopdesigns.props;

import org.snoopdesigns.props.services.DataLoaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    @Value("${loadOnStart}")
    private static String loadOnStart;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        boolean loadOnStartFlag = Boolean.parseBoolean(loadOnStart);
        if (loadOnStartFlag) {
            context.getBean(DataLoaderService.class).loadData();
        }
    }
}
