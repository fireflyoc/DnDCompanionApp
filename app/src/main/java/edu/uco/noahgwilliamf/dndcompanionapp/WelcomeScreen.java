package edu.uco.noahgwilliamf.dndcompanionapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class WelcomeScreen extends AppCompatActivity {

    private DrawerLayout drawer;
    private ListView menuList;
    private ImageButton menuButton;
    private NavigationDrawerClickListener navi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        navi = new NavigationDrawerClickListener(this,getResources());


        drawer = (DrawerLayout) findViewById(R.id.main_layout);
        menuList = (ListView) findViewById(R.id.main_menu);
        menuButton = (ImageButton) findViewById(R.id.menuButton);

        menuList.setAdapter(navi.getListAdapter());
        menuList.setOnItemClickListener(navi);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

    }







}
