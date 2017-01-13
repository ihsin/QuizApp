package com.rahul.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    int score_value=0;
    String [] questions=
            {"1.Which will legally declare, construct, and initialize an array?",
            "2.Which is a reserved word in the Java programming language?",
            "3.Which is a valid keyword in java?",
            "4.Which is a valid declarations of a String?",
            "5.Which is the valid declarations within an interface definition?",
            "6.What happens when thread's yield() method is called?",
            "7.This is the parent of Error and Exception classes.",
            "8.What is the default value of float variable?",
            "9.Static binding uses which information for binding?",
            "10.which operator is considered to be with highest precedence?"};
    String [][] options=
            {
                    {"int [] myList = {\"1\", \"2\", \"3\"};","int [] myList = (5, 8, 2);","int myList [] [] = {4,9,7,0};","int myList [] = {4, 3, 7};"},
                    {"method","native","subclass","reference"},
                    {"interface","string","Float","unsigned"},
                    {"String s1 = null;","String s2 = 'null';","String s3 = (String) 'abc';","String s4 = (String) '\\ufeed';"},
                    {"public double method();","public final double method();","static void method(double d1);","protected void method(double d1);"},
                    {"Thread returns to the ready state.","Thread returns to the waiting state.","Thread starts running."," None of the above."},
                    {"Throwable","Catchable","MainError","MainException"},
                    {"0.0d","0.0f","0","not defined"},
                    {"type.","object.","Both of the above.","None of the above."},
                    {"() , [].","=","?:","%"}
            };
    String[] correctAnswers=
            {"int myList [] = {4, 3, 7};",
                    "native",
                    "interface",
                    "String s1 = null;",
                    "public double method();",
                    "Thread returns to the ready state.",
                    "Throwable",
                    "0.0f",
                    "type.",
                    "() , []."
            };
    int[] indexOfAnswers={3,1,0,0,0,0,0,1,0,0};
    int clickcount=0;
    int questionAttempted=0;
    RadioButton radiobutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//-------------------------------------------------------------------------------------------------
        final TextView qn=(TextView)findViewById(R.id.question);
        final TextView score=(TextView)findViewById(R.id.score);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        final Button button=(Button)findViewById(R.id.button1);
        final Button skip=(Button)findViewById(R.id.button2);
        final ProgressBar progress=(ProgressBar)findViewById(R.id.progress);
//-------------------------------------------------------------------------------------------------
        qn.setText(questions[0]);
        for (int i = 0; i < 4; i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(options[0][i]);
        }
        score.setText("SCORE: "+String.valueOf(score_value));
 //------------------------------------------------------------------------------------------------
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, int i) {

    if(radioGroup.getCheckedRadioButtonId() == -1)
        button.setClickable(false);
    else
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int id=radioGroup.getCheckedRadioButtonId();
                radiobutton=(RadioButton)findViewById(id);
                String selectedAnswer=radiobutton.getText().toString();
                if(selectedAnswer==correctAnswers[clickcount])
                {
                    Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
                    score_value++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Answer", Toast.LENGTH_SHORT).show();
                    score_value--;
                }
                questionAttempted++;
                clickcount++;
                progress.setProgress(clickcount);
                if(clickcount<10) {
                    qn.setText(questions[clickcount]);
                    for (int i = 0; i < 4; i++) {
                        ((RadioButton) radioGroup.getChildAt(i)).setText(options[clickcount][i]);
                    }
                    score.setText("SCORE: " + String.valueOf(score_value));
                    radioGroup.clearCheck();
                }
                else {
                    MainActivity.this.setContentView(R.layout.activity_main2);
                        TextView score=(TextView)findViewById(R.id.scored);
                        score.setText("TOTAL SCORE: " + String.valueOf(score_value));
                        TextView QnSkipped=(TextView)findViewById(R.id.questionskipped);
                        TextView QnAttempted=(TextView)findViewById(R.id.questionAttempted);
                        QnSkipped.setText("QUESTIONS SKIPPED: "+String.valueOf(10-questionAttempted));
                        QnAttempted.setText("QUESTIONS ATTEMPTED: "+String.valueOf(questionAttempted));
                }

            }
        });
            }
        });
        //------------------------------------------------------------------------------------------
        skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickcount++;
                progress.setProgress(clickcount);
                if(clickcount<10) {
                    qn.setText(questions[clickcount]);
                    for (int i = 0; i < 4; i++) {
                        ((RadioButton) radioGroup.getChildAt(i)).setText(options[clickcount][i]);
                    }
                    score.setText("SCORE: " + String.valueOf(score_value));
                    radioGroup.clearCheck();
                }
                else {
                    MainActivity.this.setContentView(R.layout.activity_main2);
                    TextView score=(TextView)findViewById(R.id.scored);
                    score.setText("TOTAL SCORE: " + String.valueOf(score_value));
                    TextView QnSkipped=(TextView)findViewById(R.id.questionskipped);
                    TextView QnAttempted=(TextView)findViewById(R.id.questionAttempted);
                    QnSkipped.setText("QUESTIONS SKIPPED: "+String.valueOf(10-questionAttempted));
                    QnAttempted.setText("QUESTIONS ATTEMPTED: "+String.valueOf(questionAttempted));
                }         }
        });
        //------------------------------------------------------------------------------------------
    }
    //----------------------------------------------------------------------------------------------

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //   outState.putString(savedName, name);
    }
}
