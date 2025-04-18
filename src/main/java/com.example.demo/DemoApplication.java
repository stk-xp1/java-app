package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class DemoApplication {

    @GetMapping("/api/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Podman container!";
    }


    @GetMapping(value="/api/index", produces = MediaType.TEXT_HTML_VALUE )
    @ResponseBody
    public String index(@value("classpath:public/index.html") Resource resource) throw IOException {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    @GetMapping("/api/goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye from Podman container!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

