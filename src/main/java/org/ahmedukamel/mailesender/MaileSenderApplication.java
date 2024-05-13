package org.ahmedukamel.mailesender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class MaileSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaileSenderApplication.class, args);
    }

    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

}
