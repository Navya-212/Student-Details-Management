package com.example.s2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText regNoInput,nameInput,branchInput,marksInput,rollToFetchInput;
    Button btnSave,btnFetch;
    TextView studentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler db=new DataBaseHandler(this);

        regNoInput=findViewById(R.id.regNoInput);;
        nameInput=findViewById(R.id.nameInput);
        branchInput= findViewById(R.id.branchInput);
        marksInput=findViewById(R.id.marksInput);
        rollToFetchInput=findViewById(R.id.fetchRegInput);

        btnSave=findViewById(R.id.btnSave);
        btnFetch=findViewById(R.id.btnFetch);

        studentInfo=findViewById(R.id.studentInfo);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addStudent(
                        new Student(
                                regNoInput.getText().toString(),
                                nameInput.getText().toString(),
                                branchInput.getText().toString(),
                                Integer.parseInt(marksInput.getText().toString())
                        )
                );
                Log.d("STUDENT","Saved...");
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=db.getStudent(rollToFetchInput.getText().toString());
                studentInfo.setText(
                        "RegNo: "+ student.getRegNo()+ "\n" +
                                "Name: "+student.getName()+ "\n"+
                                "Branch: "+student.getBranch()+ "\n"+
                                "Marks: "+ student.getMarks()
                );
            }
        });
    }
}