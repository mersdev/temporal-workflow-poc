package com.xdman.temporalpoc.controller;

import com.xdman.temporalpoc.service.HelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private final HelloWorldService service;

    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String greet(){
        return service.startWorkFlow();
    }
    @GetMapping("/bye")
    public void bye(){
        service.sendBye();
    }
    @GetMapping("/france")
    public void france(){
        service.sendFrance();
    }
    @GetMapping("/message")
    public String getMessage(){
        return service.getMessage();
    }
}
