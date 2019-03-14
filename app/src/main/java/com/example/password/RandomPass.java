package com.example.password;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class RandomPass extends AppCompatActivity {

    SeekBar seekBar;
    TextView LinghtText;

    Button generationButton;

    CheckBox Check1; CheckBox Check2; CheckBox Check3; CheckBox Check4;

    //определим массивы с символами
    char[] lowercase_letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char[] capital_letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    char[] number_of = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] symbols = {'@', '(', ')', '!','?'  };



    int progress2 = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_pass);

        seekBar= (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(36);

        seekBar.setProgress(progress2);

        LinghtText = (TextView) findViewById(R.id.TextSeekBar);

        LinghtText.setText("Длина пароля: " + progress2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress2 = progress;
                LinghtText.setText("Длина пароля: " + progress2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });     //перемещене ползунка


        //занимаемся checBox-ами
        Check1= (CheckBox) findViewById(R.id.Chec1);
        Check2= (CheckBox) findViewById(R.id.Chec2);
        Check3= (CheckBox) findViewById(R.id.Chec3);
        Check4= (CheckBox) findViewById(R.id.Chec4);

        generationButton = (Button) findViewById(R.id.generation_random_pass);

        generationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check1.isChecked()==false && Check2.isChecked()==false &&
                        Check3.isChecked()==false && Check4.isChecked()==false){
                    Toast Err = Toast.makeText(getApplicationContext(), R.string.Error_rand_1, Toast.LENGTH_LONG);
                    Err.show();
                    return;
                }
                if (progress2==0){
                    Toast Err_null = Toast.makeText(getApplicationContext(), R.string.Error_rand_2, Toast.LENGTH_LONG);
                    Err_null.show();
                    return;
                }
                //формируем массив для генерации пароля
                ArrayList<Character> pass = new ArrayList<Character>();
                if (Check1.isChecked()){
                    for (char c : lowercase_letters){
                        pass.add(c);
                    }
                }
                if (Check2.isChecked()){
                    for (char c : capital_letters){
                        pass.add(c);
                    }
                }
                if (Check3.isChecked()){
                    for (char c : number_of){
                        pass.add(c);
                    }
                }
                if (Check4.isChecked()){
                    for (char c : symbols){
                        pass.add(c);
                    }
                }
                Intent intentCopy = new Intent(RandomPass.this, CopyActivity.class);
                String pas = PasswordRand(pass, progress2);
                intentCopy.putExtra("pas", pas);
                startActivity(intentCopy);
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
                Intent Inf_int = new Intent( this, HelpRandActivity.class);
                startActivity (Inf_int);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String PasswordRand(ArrayList<Character> pass, int progress2){
        String pas = "";
        Random rand = new Random();
        int ind;
        for (int i=0; i<progress2; i++){
            ind = rand.nextInt(pass.size());
            pas += pass.get(ind);
        }
        return pas;
    }
}
