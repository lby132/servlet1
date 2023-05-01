package hello.servlet1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Servlet1Application {

    public static void main(String[] args) {
        SpringApplication.run(Servlet1Application.class, args);
    }

}
