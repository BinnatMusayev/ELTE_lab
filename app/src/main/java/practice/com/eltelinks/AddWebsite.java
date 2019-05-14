package practice.com.eltelinks;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import practice.com.eltelinks.model.Website;
import practice.com.eltelinks.view_model.WebsiteViewModel;

public class AddWebsite extends AppCompatActivity {

    private Button submit, cancel;
    private EditText title, url;
    String titleText, urlText;
    //View model
    private WebsiteViewModel websiteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);

        //setting action bar
        Toolbar toolbar = findViewById(R.id.toolbar_add_website);
        setSupportActionBar(toolbar);

        submit = findViewById(R.id.add_ws_submit);
        cancel = findViewById(R.id.add_ws_cancel);
        title = findViewById(R.id.add_ws_title);
        url = findViewById(R.id.add_ws_url);

        websiteViewModel = ViewModelProviders.of(this).get(WebsiteViewModel.class);

        final Context c= getBaseContext();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleText = title.getText().toString().trim();
                urlText = url.getText().toString().trim();

                if ( TextUtils.isEmpty(titleText)
                        || TextUtils.isEmpty(urlText)){
                    Toast.makeText(c, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(Patterns.WEB_URL.matcher(urlText).matches()){
                        if (!urlText.startsWith("http")){
                            urlText = "http://" + urlText;
                        }
                        Website w = new Website(titleText, urlText);
                        websiteViewModel.addWebsite(w);
                        finish();
                    }else{
                        Toast.makeText(c, "URL is not correct", Toast.LENGTH_SHORT).show();
                    }
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
