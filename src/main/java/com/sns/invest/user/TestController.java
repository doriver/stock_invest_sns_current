package com.sns.invest.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/t12")
public class TestController {

    @GetMapping("/log")
    @ResponseBody
    public String logTest() {
        log.error("error from log");
        log.warn("warn from log");
        log.info("info from log");
        log.debug("debug from log");
        log.trace("trace from log");
        return "suc";
    }
}
