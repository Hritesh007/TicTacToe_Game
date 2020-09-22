package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] combo1=new String[]{"11","12","13"};
    private String[] combo2=new String[]{"21","22","23"};
    private String[] combo3=new String[]{"31","32","33"};
    private String[] combo4=new String[]{"11","21","31"};
    private String[] combo5=new String[]{"12","22","32"};
    private String[] combo6=new String[]{"13","23","33"};
    private String[] combo7=new String[]{"11","22","33"};
    private String[] combo8=new String[]{"13","22","31"};

    private ArrayList<winningCombo> winningCombinations;

    private ImageView miv11,miv12,miv13,miv21,miv22,miv23,miv31,miv32,miv33;
    private TextView mtvp1,mtvp2,mtvplayer_won;
    private Button mresetBtn;
    //private ImageView miv;
    private Boolean isPlayer1=true;
    private ArrayList<String> player1Combo=new ArrayList<>();
    private ArrayList<String> player2Combo=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winningCombinations=new ArrayList<>();
        createWinningCombinations();
        miv11=findViewById(R.id.iv_11);
        miv12=findViewById(R.id.iv_12);
        miv13=findViewById(R.id.iv_13);
        miv21=findViewById(R.id.iv_21);
        miv22=findViewById(R.id.iv_22);
        miv23=findViewById(R.id.iv_23);
        miv31=findViewById(R.id.iv_31);
        miv32=findViewById(R.id.iv_32);
        miv33=findViewById(R.id.iv_33);



        mtvp1=findViewById(R.id.tv_p1);
        mtvp2=findViewById(R.id.tv_p2);

        mtvplayer_won=findViewById(R.id.player_won);

        mresetBtn=findViewById(R.id.resetBtn);

        miv11.setOnClickListener(this);
        miv12.setOnClickListener(this);
        miv13.setOnClickListener(this);
        miv21.setOnClickListener(this);
        miv22.setOnClickListener(this);
        miv23.setOnClickListener(this);
        miv31.setOnClickListener(this);
        miv32.setOnClickListener(this);
        miv33.setOnClickListener(this);

        mtvp2.setAlpha(0.5f);
        mresetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }
    private void resetGame()
    {
        System.out.println("Reset Button Called");

        mtvplayer_won.setText("");

        miv11.setImageResource(0);
        miv12.setImageResource(0);
        miv13.setImageResource(0);
        miv21.setImageResource(0);
        miv22.setImageResource(0);
        miv23.setImageResource(0);
        miv31.setImageResource(0);
        miv32.setImageResource(0);
        miv33.setImageResource(0);

        miv11.setOnClickListener(this);
        miv12.setOnClickListener(this);
        miv13.setOnClickListener(this);
        miv21.setOnClickListener(this);
        miv22.setOnClickListener(this);
        miv23.setOnClickListener(this);
        miv31.setOnClickListener(this);
        miv32.setOnClickListener(this);
        miv33.setOnClickListener(this);

        mtvp2.setAlpha(0.5f);
        isPlayer1=true;
        player1Combo=new ArrayList<>();
        player2Combo=new ArrayList<>();

    }
    private void createWinningCombinations(){
        winningCombo combinations1=new winningCombo();
        combinations1.combinations= new ArrayList<String>(Arrays.asList(combo1));

        winningCombo combinations2=new winningCombo();
        combinations2.combinations= new ArrayList<String>(Arrays.asList(combo2));

        winningCombo combinations3=new winningCombo();
        combinations3.combinations= new ArrayList<String>(Arrays.asList(combo3));

        winningCombo combinations4=new winningCombo();
        combinations4.combinations= new ArrayList<String>(Arrays.asList(combo4));

        winningCombo combinations5=new winningCombo();
        combinations5.combinations= new ArrayList<String>(Arrays.asList(combo5));

        winningCombo combinations6=new winningCombo();
        combinations6.combinations= new ArrayList<String>(Arrays.asList(combo6));

        winningCombo combinations7=new winningCombo();
        combinations7.combinations= new ArrayList<String>(Arrays.asList(combo7));

        winningCombo combinations8=new winningCombo();
        combinations8.combinations= new ArrayList<String>(Arrays.asList(combo8));

        winningCombinations.add(combinations1);
        winningCombinations.add(combinations2);
        winningCombinations.add(combinations3);
        winningCombinations.add(combinations4);
        winningCombinations.add(combinations5);
        winningCombinations.add(combinations6);
        winningCombinations.add(combinations7);
        winningCombinations.add(combinations8);



    }

    @Override
    public void onClick(View v) {
        if(v==miv11){
            addCombo("11",v);
        }else if(v==miv12){
            addCombo("12",v);
        }else if(v==miv13){
            addCombo("13",v);
        }else if(v==miv21){
            addCombo("21",v);
        }else if(v==miv22){
            addCombo("22",v);
        }else if(v==miv23){
            addCombo("23",v);
        }else if(v==miv31){
            addCombo("31",v);
        }else if(v==miv32){
            addCombo("32",v);
        }else if(v==miv33){
            addCombo("33",v);
        }

    }
    private void addCombo(String combination,View v)
    {
        checkPlayerWon();
        ImageView miv=(ImageView)v;

        if(isPlayer1)
        {
            player1Combo.add(combination);
            miv.setImageResource(R.drawable.ic_x);
            isPlayer1=false;
            mtvp2.setAlpha(1.0f);
            mtvp1.setAlpha(0.5f);
        }
        else{
            player2Combo.add(combination);
            miv.setImageResource(R.drawable.ic_o);
            isPlayer1=true;
            mtvp2.setAlpha(0.5f);
            mtvp1.setAlpha(1.0f);
        }
        

    }
    private void checkPlayerWon()
    {
        int totalPlayCount=9;
        int currentPlayedCount=player1Combo.size()+player2Combo.size();

        for(int i=0;i<winningCombinations.size();i++) {
            if (player1Combo.containsAll(winningCombinations.get(i).combinations)) {
                System.out.println("player 1 won");
                mtvplayer_won.setText("Player 1 WON");
                endgame();
            }
            else if (player2Combo.containsAll(winningCombinations.get(i).combinations)) {
                System.out.println("player 2 won");
                mtvplayer_won.setText("Player 2 WON");
                endgame();
            }
            else if(currentPlayedCount>=totalPlayCount){
                mtvplayer_won.setText("Match Draw");
                endgame();
            }
        }
    }
    private void endgame(){
        System.out.println("End Game Called");
        miv11.setOnClickListener(null);
        miv12.setOnClickListener(null);
        miv13.setOnClickListener(null);
        miv21.setOnClickListener(null);
        miv22.setOnClickListener(null);
        miv23.setOnClickListener(null);
        miv31.setOnClickListener(null);
        miv32.setOnClickListener(null);
        miv33.setOnClickListener(null);
    }

    class winningCombo
    {
        ArrayList<String> combinations;

    }

}