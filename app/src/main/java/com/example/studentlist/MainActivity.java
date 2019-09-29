package com.example.studentlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  Viewer{
    static final String TAG_ACTIVITY = "Main";
    RecyclerView rvStudents;
    Button bCreate;

    EditText edFirstName;
    EditText edLastName;
    Button bSave;
    Button bDelete;
    CheckBox checkMan;

    AdapterStudents adapterStudents = null;
    static Controller controller = null;


    @Override
    public void setPersonalDate(String firstName, String lastName, boolean man, int ImageID) {
        edFirstName.setText(firstName);
        edLastName.setText(lastName);
        checkMan.setChecked(man);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG_ACTIVITY,"onCreate");

        rvStudents = findViewById(R.id.recycler_view_students);
        bCreate = findViewById(R.id.button_create);

        edFirstName = findViewById(R.id.editText_first_name);
        edLastName = findViewById(R.id.editText_last_name);

        bSave = findViewById(R.id.button_save);
        bDelete = findViewById(R.id.button_delete);
        checkMan = findViewById(R.id.checkBox_man);

//        if(adapterStudents == null){
//            List<Student> students = new ArrayList<>(10);
//            students.add(new Student("Eduard","Kasparovich",true,0));
//            adapterStudents = new AdapterStudents(this,students);
//        }
        adapterStudents = new AdapterStudents(this,null);
        if(controller == null) controller = new Controller();
        controller.connectViewer(this);
        adapterStudents.setListener(new AdapterStudents.Listener() {
            @Override
            public void clickPosition(int position) {
                controller.SelectStudent(position);
            }
        });

        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.CreateStudent();
            }
        });
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.Save(edFirstName.getText().toString(),edLastName.getText().toString(),checkMan.isChecked());
            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.Delete();
            }
        });

        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setAdapter(adapterStudents);
    }

    public void selectStudent(@NonNull Student student){
          edFirstName.setText(student.getFirstName());
          edLastName.setText(student.getLastName());
          checkMan.setChecked(student.isMan());
    }

    @Override
    public void setAdapterDate(List<Student> students) {
        adapterStudents.setListStudents(students);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_ACTIVITY,"onDestroy");
        controller.disconnectViewer();
    }
}
