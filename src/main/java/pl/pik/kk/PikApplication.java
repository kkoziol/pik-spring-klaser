package pl.pik.kk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PikApplication{

    public static void main(String[] args) {
            SpringApplication springApp = new SpringApplication();
            springApp.run(PikApplication.class, args);
    }

}