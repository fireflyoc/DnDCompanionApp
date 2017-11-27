package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 11/24/2017.
 */

public class DiceRollerActivity extends Activity {

    private DrawerLayout drawer;
    private ListView menuList;
    private ImageButton menuButton;
    private NavigationDrawerClickListener navi;
    private static final Random RANDOM = new Random();
    private Spinner numDiceSpinner, diceTypeSpinner;
    private String dieType;
    private ImageView die1, die2, die3, die4, die5, die6, die7, die8, die9;
    private int numDie, res1, res2, res3, res4, res5, res6, res7, res8, res9;
    private Button rollButton;
    private ArrayList<ImageView> dice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_roller_layout);

        die1 = (ImageView) findViewById(R.id.dicePick1);
        die2 = (ImageView) findViewById(R.id.dicePick2);
        die3 = (ImageView) findViewById(R.id.dicePick3);
        die4 = (ImageView) findViewById(R.id.dicePick4);
        die5 = (ImageView) findViewById(R.id.dicePick5);
        die6 = (ImageView) findViewById(R.id.dicePick6);
        die7 = (ImageView) findViewById(R.id.dicePick7);
        die8 = (ImageView) findViewById(R.id.dicePick8);
        die9 = (ImageView) findViewById(R.id.dicePick9);
        rollButton = (Button) findViewById(R.id.rollButton);

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);
        dice.add(die7);
        dice.add(die8);
        dice.add(die9);




        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation anim1 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim2 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim3 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim4 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim5 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim6 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim7 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim8 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final Animation anim9 = AnimationUtils.loadAnimation(DiceRollerActivity.this, R.anim.dice_roll);
                final ArrayList<Animation> animations = new ArrayList<Animation>();
                animations.add(anim1);
                animations.add(anim2);
                animations.add(anim3);
                animations.add(anim4);
                animations.add(anim5);
                animations.add(anim6);
                animations.add(anim7);
                animations.add(anim8);
                animations.add(anim9);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int sides = Integer.parseInt(dieType.substring(1));
                        int value = getDiceRoll(sides);
                        int res = getResources().getIdentifier(dieType+"_"+value, "drawable", "edu.uco.noahgwilliamf");
                        for(int i=0;i<animations.size();i++){
                            if(animation == animations.get(i)){
                                dice.get(i).setImageResource(res);
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                for(Animation a: animations){
                    a.setAnimationListener(animationListener);
                }
                for(int i=0; i<numDie; i++){
                    //animations.get(i).startAnimation();
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        drawer.closeDrawers();
    }

    private static int getDiceRoll(int sides){
        return RANDOM.nextInt(sides)+1;
    }


    private void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());


        drawer = (DrawerLayout) findViewById(R.id.char_create_layout);
        menuList = (ListView) findViewById(R.id.char_create_menu);
        menuButton = (ImageButton) findViewById(R.id.char_create_menuButton);

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
}
