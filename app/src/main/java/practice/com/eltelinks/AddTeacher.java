package practice.com.eltelinks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AddTeacher extends AppCompatActivity {
    private Button submit, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        //setting action bar
        Toolbar toolbar = findViewById(R.id.toolbar_add_teacher);
        setSupportActionBar(toolbar);

        submit = findViewById(R.id.add_teacher_submit);
        cancel = findViewById(R.id.add_teacher_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
