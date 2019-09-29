package com.example.studentlist;

public class Student {
    private String firstName;
    private String lastName;
    private boolean man;
    private int imageId;

    public Student(String firstName, String lastName, boolean man, int imageId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.man = man;
        this.imageId = imageId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
