package practice.com.eltelinks;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView trashIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //for colors to make affect
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                                            R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //setting inital fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Websites_Fragment()).commit();
            navigationView.setCheckedItem(R.id.websites);
        }

        trashIcon = findViewById(R.id.trash);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START) ){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    //handling clicks on navigation view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.teachers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Teachers_Fragment()).commit();
                break;
            case R.id.websites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Websites_Fragment()).commit();
                break;
            case R.id.timetables:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Timetables_Fragment()).commit();
                break;
            case R.id.browser:
                openBrowser("https://www.elte.hu");
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openBrowser(String url){
        if (!url.startsWith("http")){
            url = "http://" + url;
        }
        Bundle bundle = new Bundle();
        bundle.putString("chosenURL", url);
        // set Fragmentclass Arguments
        Browser_Fragment bf = new Browser_Fragment();
        bf.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                bf).commit();
        navigationView.setCheckedItem(R.id.browser);
    }

    public void showTrashIcon(){
      trashIcon.setVisibility(View.VISIBLE);
    }

    public void hideTrashIcon(){
        trashIcon.setVisibility(View.GONE);
    }
}
