package org.snoopdesigns.props;

import org.snoopdesigns.props.crawler.DataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(DataLoader.class).loadData(1);
    }
}
