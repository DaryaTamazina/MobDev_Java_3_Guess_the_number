package com.example.number;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int num;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);
        final Random random = new Random();
        num = random.nextInt(5+1);
        exit = findViewById(R.id.button1);


        View.OnClickListener clickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button:
                        if (etInput.getText().length() != 0 && bControl.getText().equals(getResources().getString(R.string.input_value))) {
                            if(Integer.parseInt(etInput.getText().toString())>0 && Integer.parseInt(etInput.getText().toString())<6){
                                if(Integer.parseInt(etInput.getText().toString())>num)
                                {
                                    tvInfo.setText(getResources().getString(R.string.ahead));
                                }
                                else if (Integer.parseInt(etInput.getText().toString())<num)
                                {
                                    tvInfo.setText(getResources().getString(R.string.behind));
                                }
                                else  if (Integer.parseInt(etInput.getText().toString())==num)
                                {
                                    tvInfo.setText(getResources().getString(R.string.hit));
                                    bControl.setText(getResources().getString(R.string.play_more));
                                    num = random.nextInt(5+1);
                                }
                            }
                            else if (Integer.parseInt(etInput.getText().toString())<1 || Integer.parseInt(etInput.getText().toString())>5)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("ОШИБКА!")
                                        .setMessage("Вы должны ввести число от 1 до 100!")
                                        .setCancelable(false)
                                        .setNegativeButton("ОК",
                                                new DialogInterface.OnClickListener()
                                                {
                                                    public void onClick(DialogInterface dialog, int id)
                                                    {
                                                        dialog.cancel();
                                                    }
                                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }
                        else if (!etInput.getText().toString().equals("") && bControl.getText().toString().equals(getResources().getString(R.string.play_more)))
                        {
                            bControl.setText(getResources().getString(R.string.input_value));
                            tvInfo.setText(getResources().getString(R.string.try_to_guess));
                            etInput.setText("");
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("ОШИБКА!")
                                    .setMessage("Вы не ввели число!")
                                    .setCancelable(false)
                                    .setNegativeButton("ОК",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id)
                                                {
                                                    dialog.cancel();
                                                }
                                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                        break;
                    case R.id.button1:
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                }
            }
        };
        bControl.setOnClickListener(clickListener);
        exit.setOnClickListener(clickListener);

    }
}

