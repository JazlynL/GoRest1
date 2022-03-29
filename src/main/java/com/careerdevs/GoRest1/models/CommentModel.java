package com.careerdevs.GoRest1.models;

public class CommentModel {
    private int id;
    private int postid;
    private String name;
    private String email;
    private String body;

   public CommentModel(){

   }

    public int getId() {
        return id;
    }

    public int getPostid() {
        return postid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "id=" + id +
                ", post_id=" + postid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
