package com.example.studentlist;

import java.util.List;

public interface Viewer {
    void setAdapterDate(List<Student> students);
    void setPersonalDate(String firstName,String lastName,boolean man,int ImageID);
}
