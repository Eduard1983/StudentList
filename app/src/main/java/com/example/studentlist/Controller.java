package com.example.studentlist;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    final static String DEFAULT_FIRST_NAME = "New";
    final static String DEFAULT_LAST_NAME = "Student";
    final static int DEFAULT_IMAGE_ID = 0;
    final static boolean DEFAULT_MAN = true;
    Viewer viewer;

    int currentPosition;

    private List<Student> listStudents; //Model

    public Controller(){
        listStudents = new ArrayList<>(20);
        currentPosition = -1;
        viewer = null;
    }

    public void SelectStudent(int position){
        if(position >= listStudents.size()) return;
        currentPosition = position;
        Student student = listStudents.get(position);
        if(viewer != null) viewer.setPersonalDate(student.getFirstName(),student.getLastName(),
                student.isMan(),student.getImageId());
    }

    public void CreateStudent(){
        Student student = new Student(DEFAULT_FIRST_NAME,DEFAULT_LAST_NAME,DEFAULT_MAN,DEFAULT_IMAGE_ID);
        listStudents.add(student);
        currentPosition++;
        updateDate();
    }

    public void Save(String firstName, String lastName, boolean man){
        if(currentPosition < 0) return;
        Student student = listStudents.get(currentPosition);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMan(man);
        updateDate();
    }

    public void Delete(){
        if(currentPosition < 0) return;
        listStudents.remove(currentPosition);
        currentPosition--;
        updateDate();
    }

    public void connectViewer(Viewer viewer){
        this.viewer = viewer;
        updateDate();
    }

    public void disconnectViewer(){
        viewer = null;
    }

    private void updateDate(){
        if(viewer != null) viewer.setAdapterDate(listStudents);
    }
}
