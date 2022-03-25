package com.careerdevs.GoRest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
           String apiToken = env.getProperty("API_TOKEN");



           //adding a query param
           url+="?access-token=" + apiToken;



           return restTemplate.getForObject(url, Object.class);


       }
       catch (Exception exception ){
           return "404:user not found dope "+ userId;
       }


    }








    //Creating a Delete Request

    @DeleteMapping("/{id}")
    public Object deleteHeader( RestTemplate restTemplate,
                        @PathVariable("id") String id        ){
     try {
         String url = "https://gorest.co.in/public/v2/users/" + id;
         String apiKey = env.getProperty("API_TOKEN");


         //manual approach.....
//         String response = env.getProperty("API_TOKEN");
//// implementing HTTP headers annotation to create a new header.
//         HttpHeaders headers = new HttpHeaders();
//         //giving the key authorization
//         headers.setBearerAuth(response);
//         //this is our response entity
//         HttpEntity  request = new HttpEntity(headers);
//         //creating a response entity object
//// using the original template then we will refactor.
//         restTemplate.exchange(
//                 url,
//                 HttpMethod.DELETE,
//                 request,
//                 Object.class
//         );

         url += "?access-token=" + apiKey;
         restTemplate.delete(url, Object.class);

         return "Sucessfully deleted the user  " + id;

     }catch(Exception exception){
         return " 404 : not valid input crocky " + id;
     }
    }
}
