package com.nik_makrv.adder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequestMapping("/adder")
@RestController
public class MainController {

    AtomicInteger counter = new AtomicInteger();
    volatile int max = 0;

    @GetMapping("/add")
    public int add(@Param("a") int a, @Param("b") int b) {
        counter.incrementAndGet();

        int old = counter.getAndDecrement();
        if (old > max) {
            max = old;
            log.info("max={}", old);
        }

        return a + b;
    }

}
