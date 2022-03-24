package com.careerdevs.GoRest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
// we are requesting the route that we will be using to request data
@RequestMapping("/api/users")
public class GoRestApp {
    // to access data within the application properties/API Token
    @Autowired
    Environment env;

    // test run
    @GetMapping("/goresttoke")
    public String getToken(){
        return  env.getProperty("API_TOKEN");
    }


    //sendHeader API Access

    // url we will be using  https://gorest.co.in/public/v2/users/

    @GetMapping("/{id}")
    public Object getHeader(RestTemplate restTemplate,
                            //Sending a request using a singular piece of data
                            @PathVariable("id") String userId ){
       try {
           String url = "https://gorest.co.in/public/v2/users/" + userId;
          // Object response = env.getProperty("API_TOKEN");
           return restTemplate.getForObject(url, Object.class);
       }
       catch (Exception exception ){
           return "404:user not found dope "+ userId;
       }


    }
}
