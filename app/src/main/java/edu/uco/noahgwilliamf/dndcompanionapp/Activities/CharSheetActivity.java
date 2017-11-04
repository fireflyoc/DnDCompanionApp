package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.XMLReader;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.PlayerCharacter;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 10/27/2017.
 */

public class CharSheetActivity extends Activity {

    private DrawerLayout drawer;
    private ListView menuList;
    private ImageButton menuButton;
    private NavigationDrawerClickListener navi;
    private TextView charName;
    private Intent intent;
    private PlayerCharacter pc1 = null, pc2 = null, pc3 = null;
    private XMLReader reader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_sheet_stats);
        intent = getIntent();

        reader = new XMLReader();

        charName = (TextView) findViewById(R.id.charNameTextView);
        charName.setText(intent.getStringExtra("CharName"));


        setUpNavi();

        try {
            setUpChars();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setUpChars() throws XmlPullParserException, IOException {
        switch (intent.getIntExtra("charNum",0)) {
            case 0:
                if (pc1 == null) {
                    pc1 = reader.readChar("character1");
                }
                setUpDisplay(pc1);
                break;
            case 1:
                if (pc2 == null) {
                    pc2 = reader.readChar("character2");
                }
                setUpDisplay(pc2);
                break;
            case 2:
                if (pc3 == null) {
                    pc3 = reader.readChar("character3");
                }
                setUpDisplay(pc3);
                break;
            default:
                System.out.println("Error, got sent an invalid Character");
        }

    }

    private void setUpDisplay(PlayerCharacter pc) {
        //takes values from the PlayerCharacter passed to it and puts them in the correct spot in the layout
    }


    @Override
    protected void onResume() {
        super.onResume();
        drawer.closeDrawers();
    }


    private void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());


        drawer = (DrawerLayout) findViewById(R.id.charSheet_layout);
        menuList = (ListView) findViewById(R.id.charSheetMenu);
        menuButton = (ImageButton) findViewById(R.id.charSheetMenuButton);

        if (navi != null) {
            System.out.println("navi wasnt null setting up menu");
            if (menuList != null) {
                menuList.setAdapter(navi.getListAdapter());
                menuList.setOnItemClickListener(navi);
            } else {
                System.out.println("menuList was null");
            }
        } else {
            System.out.println("NAVI WAS NULL!");
        }

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CLICKED Me");
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }


}//end class
