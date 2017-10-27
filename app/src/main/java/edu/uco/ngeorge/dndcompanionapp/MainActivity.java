package edu.uco.ngeorge.dndcompanionapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.main_layout);
        menuList = (ListView) findViewById(R.id.main_menu);
        menuList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,getResources().getStringArray(R.array.menu)));
        menuList.setOnItemClickListener(new DrawerItemClickListener());

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
