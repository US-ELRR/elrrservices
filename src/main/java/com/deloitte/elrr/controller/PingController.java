/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@Slf4j
public class PingController {
    /**
     *
     * @return Map<String, String>
     */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> pong = new HashMap<>();
        log.info("Inside PING Controller.........");
        pong.put("pong", "Hello 1, ELRR!");
        return pong;
    }
}
