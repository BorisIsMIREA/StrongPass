package com.example.password;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    TextView Number;
    TextView Pin;

    Button Colc;

    public int[] N = new int[11];
    public int[] P = new int [4];

    public char[] Kod = {'a', 'b', 'c','d','e','f','j','h','i','j','1','2','3','k','l','m','n','o','p','q','r','s','t',
            'u','v','w','4','6','5','x','z','y','A','B','C','D','I','F','G','H','I','J','7','8','9','K','L','M','N','O','P',
            'Q','R','S','T','U','V','@','W','X',')','Y','(','Z'};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Number = (TextView) findViewById(R.id.number_text);
        Pin = (TextView) findViewById(R.id.PIN_text);

        Colc= (Button) findViewById(R.id.button);


        Colc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, CopyActivity.class);
                try{
                    String sum = Number.getText().toString();
                    NUMBER(sum);

                    String pin = Pin.getText().toString();
                    PIN(pin);
                }
                catch (NumberFormatException e){
                }

                String pas = Password2(P, N);
                intent.putExtra("pas", pas);       //pass to StartActivity
                startActivity(intent);
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.munu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.Pin_menu:
                Intent Pin_int = new Intent (this, StartActivity.class);
                startActivity(Pin_int);
                return true;

            case R.id.Rand_menu:
                Intent Rand_int = new Intent (this, RandomPass.class);
                startActivity(Rand_int);
                return true;

            case R.id.Info_menu:
                Intent Inf_int = new Intent( this, HelpActivity.class);
                startActivity (Inf_int);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void PIN (String p){
        if (p.length()<4){
            while(p.length()<4)
                p+='1';
        }
        char[] Pi = p.toCharArray();

        for (int i = 0; i < 3; i++) {
            P[i] = Character.getNumericValue(Pi[i]);
        }
    }

    private void NUMBER(String sum){
        if (sum.length()<11){
            while (sum.length()<11)
                sum+='2';
        }
        char[] Nu = sum.toCharArray();
        for (int i=0; i<11; i++){
            N[i] = Character.getNumericValue(Nu[i]);
        }
    }

    private String Password2(int[] P, int[] N){
        String pas = "";
        int X;
        for (int i=0; i<3; i++){
            X = N[i]*10 + P[0];
            if (X>63)
                X-=38;
            pas+=Kod[X];
            X=P[0]*4+N[i];
            pas+=Kod[X];
        }
        for (int i = 3; i < 6; i++)
        {
            X = N[i] * 10 + P[1];
            if (X > 63)
                X -= 41;
            pas += Kod[X];
            X = P[1] * 10 + N[i];
            if (X > 63)
                X -= 63;
            pas += Kod[X];
        }
        for (int i = 6; i < 9; i++)
        {
            X = N[i] * 10 + P[2];
            if (X > 63)
                X -= 45;
            pas += Kod[X];
            X = P[2] * 2 + N[i]*3;
            pas += Kod[X];
        }
        for (int i = 9; i < 11; i++)
        {
            X = N[i] * 10 + P[3] + 1;
            if (X > 63)
                X -= 50;
            pas += Kod[X];
            X = P[3] * 10 + N[i];
            if (X > 63)
                X -= 44;
            pas +=  Kod[X];
        }
        return pas;
    }
}
