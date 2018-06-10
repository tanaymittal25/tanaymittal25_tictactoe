package com.example.tanay.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    EditText player1,player2;
    Button start;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        player1 = (EditText)findViewById(R.id.editText);
        player2 = (EditText)findViewById(R.id.editText2);
        relativeLayout = (RelativeLayout)findViewById(R.id.RR);
        start = (Button)findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = player1.getText().toString().toUpperCase();
                String s2 = player2.getText().toString().toUpperCase();

                if(s1.isEmpty() || s2.isEmpty()){
                    Toast.makeText(Main3Activity.this,"Please Enter The Player Names!!!",Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(Main3Activity.this, Main2Activity.class);

                    intent.putExtra("PLAYER1", s1);
                    intent.putExtra("PLAYER2", s2);

                    startActivity(intent);
                }

            }
        });
    }
}
