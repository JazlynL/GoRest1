package com.careerdevs.GoRest1.models;
/*
//fields we are going to use and instantiate for our application
     "id": 4317,
    "name": "Bheeshma Dhawan",
    "email": "bheeshma_dhawan@fisher.org",
    "gender": "male",
    "status": "active"
 */

public class UserModelExample {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;


    //Do Not Delete Or Change.
    public UserModelExample(){

    }

    public UserModelExample(String name, String email, String gender,String status){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String generateReport(){
        return name + " They are currently  " + status + " you are able to contact them at: " + email;
    }

    @Override
    public String toString() {
        return "UserModelExample{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
