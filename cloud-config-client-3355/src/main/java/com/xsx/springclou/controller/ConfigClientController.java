package com.xsx.springclou.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String configInfo;

    @Value("${config.version}")
    private String version;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return "port == " + port+ "\nconfigInfo ==" + configInfo + "\nversion == " + version;
    }
}
