package com.mon.pdlconverter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button b1 ,bd,bd2 ;
    Button mb1,inputbtn ,mb2, assignbtn;
    EditText et1, inputedt,assignedt;
    Lexical_Analyser la;
    List<Token> tokenList;
    Parser pa;

    CardView cd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.done);
        bd = findViewById(R.id.drawerbtn);
        bd2 = findViewById(R.id.drawerbtn2);

        mb1 = findViewById(R.id.mbutton1);
        mb2 = findViewById(R.id.mbutton2);

        inputbtn = findViewById(R.id.inpubutton);
        assignbtn = findViewById(R.id.assignbtn);

        et1 = findViewById(R.id.EdittL);
        inputedt= findViewById(R.id.inputedt);
        assignedt= findViewById(R.id.assignedt);

        la = new Lexical_Analyser();
        pa = new Parser();

        cd = findViewById(R.id.carddrawer);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s =isBalanced(et1.getText().toString()) ;


                Parser.Error = isBalanced(et1.getText().toString());

                System.out.println(Parser.Error);
                if(hasOddNumberOfQuotes(et1.getText().toString())){
                    Parser.Error = "missing double quetes";
                }

                s = "";

                if(s.equals("")) {
                    if(!Parser.Error.isEmpty()){
                        et1.setText("");
                    }
                    tokenList = la.tokenizeEditText(et1);
                    la.printTokenList(la.tokenizeEditText(et1));


                    String output = null;
                    try {
                        output = pa.Parsetostring(tokenList);


                        System.out.println(output);

                        //System.out.println(rootNode.print());
                        Intent intent = new Intent(MainActivity.this, output_activity.class);
                        intent.putExtra("half-output", output);
                        startActivity(intent);

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            }

        });

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cd.getVisibility() == View.GONE){

                    cd.setVisibility(View.VISIBLE);

                }else{
                    cd.setVisibility(View.GONE);

                }

            }
        });

        bd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cd.getVisibility() == View.VISIBLE){

                    cd.setVisibility(View.GONE);

                }else{
                    cd.setVisibility(View.VISIBLE);

                }

            }
        });

        mb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, mb1);
                popup.getMenuInflater().inflate(R.menu.rolechanger, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.option1:
                                // Perform action for Option 1
                                Lexical_Analyser.inputrole = "(?i)read";
                                 mb1.setText("read");
                                return true;
                            case R.id.option2:
                                // Perform action for Option 2
                                mb1.setText("input");
                                Lexical_Analyser.inputrole = "(?i)input";


                                return true;
                            case R.id.option3:
                                // Perform action for Option 3
                                mb1.setText("enter");
                                Lexical_Analyser.inputrole = "(?i)enter";


                                return true;

                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }

        });


        mb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, mb1);
                popup.getMenuInflater().inflate(R.menu.assignmenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.option1:
                                // Perform action for Option 1
                                Lexical_Analyser.assignrole = "(?i)assign";
                                mb2.setText("assign");
                                return true;
                            case R.id.option2:
                                // Perform action for Option 2

                                Lexical_Analyser.assignrole = "(?i)set";
                                mb2.setText("set");

                                return true;

                            case R.id.option3:
                                // Perform action for Option 3
                                Lexical_Analyser.assignrole = "(?i)make";
                                mb2.setText("make");


                                return true;

                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }

        });



        assignbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Lexical_Analyser.assignrole = "(?i)" + assignedt.getText();

            }
        });


        inputbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Lexical_Analyser.inputrole = "(?i)" + inputedt.getText();

            }
        });


    }


    public static String isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return "Unbalanced - missing opening " + getBracketType(c) + " at index " + i;
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return "Unbalanced - mismatched " + getBracketType(top) + " and " + getBracketType(c) + " at index " + i;
                }
            }
        }

        if (stack.isEmpty()) {
            return "";
        } else {
            char missingBracket = stack.pop();
            return "Unbalanced - missing closing " + getBracketType(missingBracket);
        }
    }

    private static String getBracketType(char c) {
        switch (c) {
            case '(':
            case ')':
                return "parenthesis";
            case '[':
            case ']':
                return "square bracket";
            case '{':
            case '}':
                return "curly brace";
            default:
                return "unknown bracket";
        }
    }

    public static boolean hasOddNumberOfQuotes(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') {
                count++;
            }
        }
        return count % 2 != 0;
    }



}