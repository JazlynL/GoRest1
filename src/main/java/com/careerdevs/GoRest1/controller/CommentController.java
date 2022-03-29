package com.careerdevs.GoRest1.controller;


import com.careerdevs.GoRest1.models.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    Environment env;


    @GetMapping("/firstpage")
    public CommentModel[] getFirstPage(RestTemplate restTemplate) {
    String url = "https://gorest.co.in/public/v2/comments";
    return restTemplate.getForObject(url, CommentModel[].class);

    }

    //Getting a single comment from the piece of Data

    @GetMapping("/{id}")
    public ResponseEntity getSingleComment(RestTemplate restTemplate,
                                           @PathVariable("id") int commentId ) {
        try {
            String url = "https://gorest.co.in/public/v2/comments/" + commentId;
            return  new ResponseEntity(restTemplate.getForObject(url,CommentModel.class), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteSingleComment(RestTemplate restTemplate,
                                           @PathVariable("id") int commentId ) {
        try {
            String url = "https://gorest.co.in/public/v2/comments" + commentId;
            String token = env.getProperty("API_TOKEN");
            url += "?access-token =" + token;
            CommentModel deletedComment =restTemplate.getForObject(url,CommentModel.class);
             restTemplate.delete(url);

            return  new ResponseEntity(deletedComment,HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping
    public ResponseEntity<Object>  putMapping (RestTemplate restTemplate,
        @RequestBody  CommentModel updateComment){
            try{

                String url = "https://gorest.co.in/public/v2/comments/" + updateComment.getId() ;
                String token = env.getProperty("API_TOKEN");
                url += "?access-token=" + token;
                HttpEntity<CommentModel> request = new HttpEntity<>(updateComment);
                System.out.println(url);
                System.out.println(updateComment);
                ResponseEntity<CommentModel> response = restTemplate.exchange(
                        url,
                        HttpMethod.PUT,
                        request,
                        CommentModel.class
                );


                return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);


            }catch (Exception e){
                System.out.println(e.getClass());
                System.out.println(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

