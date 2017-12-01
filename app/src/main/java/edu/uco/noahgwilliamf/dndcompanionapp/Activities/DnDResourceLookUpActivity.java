package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.R;


/**
 * Created by Decker on 10/30/2017.
 */

public class DnDResourceLookUpActivity extends Activity {

    protected DrawerLayout drawer;
    protected ListView menuList;
    protected ImageButton menuButton;
    protected NavigationDrawerClickListener navi;
    protected android.app.FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getFragmentManager();
        setUpNavi();
        doSetup();

    } //end onCreate

    protected void doSetup(){

    }

    protected void resetList(){

    }


    protected void filterList(String filter){

    }

    @Override
    protected void onResume() {
        super.onResume();
        drawer.closeDrawers();
    }


    protected void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());
    }


}//end class
