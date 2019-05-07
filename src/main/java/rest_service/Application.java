package rest_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import parsers.updates_for_events.EventUpdate;

import java.util.Date;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

        //        Test.callYourselfSimple();
//        Test.callYourself();
    }
}
