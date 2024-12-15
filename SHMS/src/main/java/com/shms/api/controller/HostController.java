package com.shms.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequiredArgsConstructor
public class HostController {

    private final Environment environment;

    @GetMapping("/host-info")
    public String getHostInfo() {
        String hostname = environment.getProperty("spring.cloud.client.hostname");
        String ipAddress = environment.getProperty("spring.cloud.client.ip-address");
        String returnValue;

        try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            returnValue = ipAddr.getHostAddress();
        } catch (UnknownHostException ex) {
            returnValue = ex.getLocalizedMessage();
        }


        return "Hostname: " + hostname + ", IP Address: " + ipAddress + ", Ip V2: " + returnValue;
    }
}