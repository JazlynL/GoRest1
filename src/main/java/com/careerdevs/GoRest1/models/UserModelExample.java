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
    private String gender;
    private String status;


    //Do Not Delete Or Change.
    public UserModelExample(){

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



    @Override
    public String toString() {
        return "UserModelExample{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
