package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

import static java.lang.Integer.parseInt;

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
    private TextView totals;
    private String dieType;
    private ImageView die1, die2, die3, die4, die5, die6, die7, die8, die9;
    private int numDie;
    private Button rollButton;
    private ArrayList<ImageView> dice;
    private ArrayList<Integer> numbers;
    String s;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_roller_layout);

        setUpNavi();

        totals = (TextView) findViewById(R.id.diceTotalText);

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

        dice = new ArrayList<>();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);
        dice.add(die7);
        dice.add(die8);
        dice.add(die9);

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

        numDiceSpinner = (Spinner) findViewById(R.id.diceNumSpinner);
        ArrayAdapter<CharSequence> numAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.numDice, R.layout.char_spinner_item);
        numAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numDiceSpinner.setAdapter(numAdapter);
        numDiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i)!=null){
                    numDie = parseInt(adapterView.getItemAtPosition(i)+"");
                    System.out.println("Number of Dice set to: "+adapterView.getItemAtPosition(i));
                    for(int j=0; j<numDie; j++){
                        dice.get(j).setVisibility(View.VISIBLE);
                    }
                    for(int j=numDie; j<dice.size(); j++){
                        dice.get(j).setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        diceTypeSpinner = (Spinner) findViewById(R.id.diceTypeSpinner);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.dice_types, R.layout.char_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diceTypeSpinner.setAdapter(typeAdapter);
        diceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i)!=null){
                    dieType = adapterView.getItemAtPosition(i)+"";
                    for(ImageView iv: dice){
                        iv.setImageResource(getResources().getIdentifier(dieType+"_1", "mipmap", "edu.uco.noahgwilliamf.dndcompanionapp"));
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Rolling "+numDie+" "+dieType);

                numbers = new ArrayList<Integer>();
                s = "Rolls: ";


                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int sides = parseInt(dieType.substring(1));
                        int value = getDiceRoll(sides);
                        System.out.println(sides + " " + value);
                        int res = getResources().getIdentifier(dieType+"_"+value, "mipmap", "edu.uco.noahgwilliamf.dndcompanionapp");
                        for(int i=0;i<animations.size();i++){
                            if(animation == animations.get(i)){
                                System.out.println("Setting dice "+i+" to "+dieType+"_"+value);
                                numbers.add(value);
                                dice.get(i).setImageResource(res);
                            }
                        }
                        setString(value+"");


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };


                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);
                anim3.setAnimationListener(animationListener);
                anim4.setAnimationListener(animationListener);
                anim5.setAnimationListener(animationListener);
                anim6.setAnimationListener(animationListener);
                anim7.setAnimationListener(animationListener);
                anim8.setAnimationListener(animationListener);
                anim9.setAnimationListener(animationListener);


                for(int i=0; i<numDie;i++){
                    dice.get(i).startAnimation(animations.get(i));

                }



            }
        });
    }

    private void setString(String value) {
        s = s+value+"  ";
        totals.setText(s);
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


        drawer = (DrawerLayout) findViewById(R.id.diceRollLayout);
        menuList = (ListView) findViewById(R.id.diceRollMenu);
        menuButton = (ImageButton) findViewById(R.id.dieRollerMenuButton);

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
