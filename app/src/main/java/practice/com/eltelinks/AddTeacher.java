package practice.com.eltelinks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import practice.com.eltelinks.model.Teacher;
import practice.com.eltelinks.view_model.TeacherViewModel;

public class AddTeacher extends AppCompatActivity {
    private Button submit, cancel;
    private EditText name, email, faculty, website;
    private TeacherViewModel teacherViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        //setting action bar
        Toolbar toolbar = findViewById(R.id.toolbar_add_teacher);
        setSupportActionBar(toolbar);

        submit = findViewById(R.id.add_teacher_submit);
        cancel = findViewById(R.id.add_teacher_cancel);
        name = findViewById(R.id.add_teacher_name);
        email = findViewById(R.id.add_teacher_email);
        faculty = findViewById(R.id.add_teacher_faculty);
        website = findViewById(R.id.add_teacher_website);


        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel.class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()
                    || email.getText().toString().isEmpty()
                    || faculty.getText().toString().isEmpty()
                    || website.getText().toString().isEmpty()){
                    Toast.makeText(AddTeacher.this, "Fill in all fields", Toast.LENGTH_SHORT);
                }else{
                    Teacher t = new Teacher(name.getText().toString(),
                                                email.getText().toString(),
                                                faculty.getText().toString(),
                                                website.getText().toString());

                    teacherViewModel.addTeacher(t);
                    finish();

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
