package cn.iocoder.engine.drools.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoyunfeng
 * @create 2024/6/26 19:06
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/helloDrools")
    public String hello() {
        return "hello";
    }
}
