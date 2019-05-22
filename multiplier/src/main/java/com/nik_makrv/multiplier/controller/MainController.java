package com.nik_makrv.multiplier.controller;

import com.nik_makrv.multiplier.client.AdderClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.IntStream;

@Slf4j
@RequestMapping("/multiplier")
@RestController
@RequiredArgsConstructor
public class MainController {

    private final AdderClient adderClient;

    @GetMapping("/multiply")
    public int add(@Param("a") int a, @Param("b") int b) {
        Date start = new Date();
        log.info("Request to multiply: a={}, b={}, result", a, b);
        boolean invert = a < 0 ^ b < 0;
        int a1 = Math.abs(a);
        int b1 = Math.abs(b);
        int result = IntStream.range(0, a1).parallel().map(x -> b1).reduce((x0, y0) -> {
            log.info("Try to add x0={}, y0={}", x0, y0);
            int addition = adderClient.add(new HashMap<>() {{
                                          put("a", x0);
                                          put("b", y0);
                                      }});
            log.info("Try to add result: x0={}, y0={}, result={}", x0, y0, addition);
            return addition;
        }).orElse(0);
        if (invert) {
            result *= -1;
        }
        log.info("Multiply result: a={}, b={}, result={}; time={} ms", a, b, result, new Date().getTime() - start.getTime());
        return result;
    }

}
