package com.example.mycalculator;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class calculator_app extends Activity{

    ImageView btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;
    ImageView btn_add,btn_sub,btn_mul,btn_div,btn_clear,btn_all,btn_dot,btn_equal;

    TextView input,output;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_layout);


        input=findViewById(R.id.input);
        output=findViewById(R.id.output);

        btn_0=findViewById(R.id.btn_0);
        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        btn_3=findViewById(R.id.btn_3);
        btn_4=findViewById(R.id.btn_4);
        btn_5=findViewById(R.id.btn_5);
        btn_6=findViewById(R.id.btn_6);
        btn_7=findViewById(R.id.btn_7);
        btn_8=findViewById(R.id.btn_8);
        btn_9=findViewById(R.id.btn_9);

        btn_add=findViewById(R.id.btn_add);
        btn_sub=findViewById(R.id.btn_sub);
        btn_mul=findViewById(R.id.btn_mul);
        btn_div=findViewById(R.id.btn_div);
        btn_clear=findViewById(R.id.btn_clear);
        btn_all=findViewById(R.id.all);
        btn_dot=findViewById(R.id.btn_dot);
        btn_equal=findViewById(R.id.btn_equal);

        btn_0.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"0");
        });

        btn_1.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"1");
        });

        btn_2.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"2");
        });

        btn_3.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"3");
        });

        btn_4.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"4");
        });

        btn_5.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"5");
        });

        btn_6.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"6");
        });

        btn_7.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"7");
        });

        btn_8.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"8");
        });

        btn_9.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+"9");
        });

        btn_dot.setOnClickListener(view -> {
            data=input.getText().toString();
            input.setText(data+".");
        });

        btn_all.setOnClickListener(view -> {
            input.setText("");
            output.setText("");
        });

        btn_clear.setOnClickListener(view -> {
            String currentText = input.getText().toString();

            if (!currentText.isEmpty()) {

                String updatedText = currentText.substring(0, currentText.length() - 1);

                input.setText(updatedText);

                output.setText("");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input.getText().toString();
                input.setText(data + "+");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input.getText().toString();
                input.setText(data + "-");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input.getText().toString();
                input.setText(data + "÷");
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = input.getText().toString();
                input.setText(data + "×");
            }
        });


        btn_equal.setOnClickListener(view -> {
            data=input.getText().toString();

            data=data.replaceAll("×", "*");
            data=data.replaceAll("÷", "/");
            data=data.replaceAll("%", "/100");

            Context rhino=Context.enter();
            rhino.setOptimizationLevel(-1);

            String finalResult="";

            Scriptable Scriptable=rhino.initStandardObjects();

            finalResult=rhino.evaluateString(Scriptable,data,"Javascript",1,null).toString();

            output.setText(finalResult);

        });

    }

}
