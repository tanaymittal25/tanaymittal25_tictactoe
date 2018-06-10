package com.example.tanay.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {


    private Button[][] buttons = new Button[3][3];

    private boolean P1Turn = true;
    private boolean Turn = true;

    private int roundCount;

    private int P1Point;
    private int P2Point;

    private TextView TVPlay1;
    private TextView TVPlay2;

    char P11,P22;
    String P1,P2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent2 = getIntent();
        
        P1=intent2.getStringExtra("PLAYER1");
        P2=intent2.getStringExtra("PLAYER2");

        P11 = P1.charAt(0);
        P22 = P2.charAt(0);

        if(P11 == P22){
            P11 = 'X';
            P22 = 'O';
        }


        TVPlay1 = findViewById(R.id.TVP1);
        TVPlay2 = findViewById(R.id.TVP2);

        TVPlay1.setText("> "+P1+" : 0");
        TVPlay2.setText("   "+P2+" : 0");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String BID = "B" + i + j;
                int RESID = getResources().getIdentifier(BID, "id", getPackageName());
                buttons[i][j] = findViewById(RESID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button Reset = findViewById(R.id.BRESET);



        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (P1Turn) {
            ((Button) v).setText(String.valueOf(P11));
        } else {
            ((Button) v).setText(String.valueOf(P22));
        }
        roundCount++;
        if(checkWin()) {
            if(P1Turn) {
                P1Win();
            }
            else {
                P2Win();
            }
        }
        else if ( roundCount == 9) {
            Draw();
        }
        else {
            P1Turn = !P1Turn;
        }
    }

    private boolean checkWin() {

        String[][] field = new String[3][3];

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0; i<3; i++) {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for(int i=0; i<3; i++) {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void P1Win(){
        P1Point++;
        Toast.makeText(this,P1+" Wins!", Toast.LENGTH_SHORT).show();
        update();
        resetBoard();
    }
    private void P2Win(){
        P2Point++;
        Toast.makeText(this,P2+" Wins!", Toast.LENGTH_SHORT).show();
        update();
        resetBoard();
    }
    private void Draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        update();
        resetBoard();
    }

    private void update() {
        if (Turn) {
            TVPlay1.setText("   " + P1 + " : " + P1Point);
            TVPlay2.setText("> " + P2 + " : " + P2Point);
        } else {
            TVPlay1.setText("> " + P1 + " : " + P1Point);
            TVPlay2.setText("   " + P2 + " : " + P2Point);
        }
    }


    private void resetBoard(){
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        Turn = !Turn;
        P1Turn = Turn;
    }

    private void resetGame(){
        P1Point=0;
        P2Point=0;
        update();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("P1Point",P1Point);
        outState.putInt("P2Point",P2Point);
        outState.putBoolean("P1Turn",P1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        P1Point = savedInstanceState.getInt("P1Point");
        P2Point = savedInstanceState.getInt("P2Point");
        P1Turn = savedInstanceState.getBoolean("P1Turn");

    }

}
