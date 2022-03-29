package com.careerdevs.GoRest1.controller;

import com.careerdevs.GoRest1.models.UserModelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
// we are requesting the route that we will be using to request data
@RequestMapping("/api/users")
public class UserController {
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



           var user=  restTemplate.getForObject(url, UserModelExample.class);
           assert user != null;
           System.out.println( "report : \n" + user.generateReport());

           return user;

       }
       catch(HttpClientErrorException.NotFound exception){
           return "user was not found in system id # "+ userId;
       }
       // creating a catch exception for a 401 client error.
       catch(HttpClientErrorException.Unauthorized exception){
           return " you are no authorized to delete this id # " + userId;
       }
       catch(Exception exception){
           System.out.println(exception.getClass());
           return exception.getMessage();
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

     }catch(HttpClientErrorException.NotFound exception){
         return "user was not found in system id # "+ id;
     }
     // creating a catch exception for a 401 client error.
      catch(HttpClientErrorException.Unauthorized exception){
         return " you are no authorized to delete this id # " +id;
      }
     catch(Exception exception){
         return " 404 : not valid input crocky " + id;
     }
    }



    //posting the data.get user data from the client side.
    @PostMapping("/qp")
    public ResponseEntity postUser(RestTemplate restTemplate,
                                   @RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("gender") String gender,
                                   @RequestParam("status") String status
    ){
        try{

            String url ="https://gorest.co.in/public/v2/users/";
            String token = env.getProperty("API_TOKEN");
            url += "?access-token="+ token;

            // we are able to create a new user by accessing th fields and implementing it within our postuser Method.
            UserModelExample user = new UserModelExample(name,email,gender,status);
//
//            HttpHeaders headers = new HttpHeaders();
//                headers.setBearerAuth(token);

            // printing out message in console;
            System.out.println("Created new user \n"+ user);

            HttpEntity<UserModelExample> request = new HttpEntity<>(user);


//            restTemplate.postForObject(url,request, UserModelExample.class);
//            url +=  "?access-token= " + token;

            return  restTemplate.postForEntity( url ,request, UserModelExample.class);


//          restTemplate.getForObject(url,request, UserModelExample.class);

        } catch(HttpClientErrorException.NotFound exception){
            //resource not found  error will output a 404 HTTP status code response
            return  new ResponseEntity("user was not found in system ", HttpStatus.NOT_FOUND);

        } catch(Exception e){
            System.out.println(e.getClass());
           // unspecified server error will output a 500 HTTP status code response
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
