package com.jantosz.azuredemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/")
    public String root() {
        LOGGER.warn("accessing demo controller / path");
        return "Hello from demo app root";
    }

    @GetMapping("/demo")
    public String demo() {
        LOGGER.warn("accessing demo controller /demo path");
        return "Hello from demo app";
    }
}
