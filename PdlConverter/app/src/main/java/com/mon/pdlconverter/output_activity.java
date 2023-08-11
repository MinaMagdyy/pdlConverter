package com.mon.pdlconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class output_activity extends AppCompatActivity {

    String half_output = "";
    EditText editout;

    Button b2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_activity);

        AlertDialog.Builder builder = new AlertDialog.Builder(output_activity.this);

        builder.setCancelable(true);
        builder.setTitle("Hello");
        //builder.setMessage(MainActivity.isBalanced(half_output));
        builder.setMessage(Parser.Error);

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                dialogInterface.cancel();


            }
        });

        if(!Parser.Error.isEmpty()) {
            builder.show();
            Parser.Error = "";
        }




        editout = findViewById(R.id.EdittLout);
        half_output = getIntent().getStringExtra("half-output");
        //String[] outs;

        //outs = half_output.split("~");

        System.out.println(half_output);
        if(Parser.Error.isEmpty()) {
            editout.setText(half_output.replace("~", "\n"));
        }else {
            editout.setText("Fix The Error To Show The Code");
        }

        b2 = findViewById(R.id.closer);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(output_activity.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });

    }



}