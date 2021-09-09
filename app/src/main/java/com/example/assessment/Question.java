package com.example.assessment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Question extends AppCompatActivity {
    private TextView Question,NoIndicator;
    private FloatingActionButton bookmarkbtn;
    private LinearLayout optionContainer;
    private Button share, next;
    private int postion=0;
    private int count=0;
    private int score=0;
    private List<questionmodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Question=findViewById(R.id.Question);
        NoIndicator=findViewById(R.id.number);
        bookmarkbtn=findViewById(R.id.Bookmarkbtn);
        optionContainer=findViewById(R.id.options_container);
        share=findViewById(R.id.share);
        next=findViewById(R.id.next);
        list= new ArrayList<>();
        list.add(new questionmodel("Question 1","A","B","C","D","c"));
        list.add(new questionmodel("Question 2","A","B","C","D","d"));
        list.add(new questionmodel("Question 3","A","B","C","D","b"));
        list.add(new questionmodel("Question 4","A","B","C","D","c"));
        list.add(new questionmodel("Question 5","A","B","C","D","c"));
        list.add(new questionmodel("Question 6","A","B","C","D","c"));
        list.add(new questionmodel("Question 7","A","B","C","D","b"));
        list.add(new questionmodel("Question 8","A","B","C","D","c"));
        list.add(new questionmodel("Question 9","A","B","C","D","a"));
        list.add(new questionmodel("Question 10","A","B","C","D","b"));
        list.add(new questionmodel("Question 11","A","B","C","D","c"));
        list.add(new questionmodel("Question 12","A","B","C","D","d"));
        list.add(new questionmodel("Question 13","A","B","C","D","c"));
        list.add(new questionmodel("Question 14","A","B","C","D","c"));
        for(int i=0;i<4;i++){
            optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }

        palyamin(Question,0,list.get(postion).getQuestion());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setEnabled(false);
                next.setAlpha( 0.7f);
                enableoption(true);
                postion++;
                if(postion==list.size()){

                    return;
                }
                count=0;
                palyamin(Question,0,list.get(postion).getQuestion());
            }
        });
    }
    private void palyamin(final View view,final int value,final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value==0&& count <4 ) {
                    String option="";
                    if (count == 0) {
                        option = list.get(postion).getOptionA();
                    } else if (count == 1) {option = list.get(postion).getOptionB();
                    } else if (count==2) {option = list.get(postion).getOptionC();
                    }else if(count==3){option = list.get(postion).getOptionD();
                    }
                    palyamin(optionContainer.getChildAt(count),0,option );
                    count++;
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if(value==0 ){
                    try{
                        ((TextView) view).setText(data);
                        NoIndicator.setText(postion+1+"/"+list.size());
                    }catch (ClassCastException ex){
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    palyamin(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption){
        enableoption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if(selectedOption.getText().toString().equals(list.get(postion).getCoorectAnswer()))//correct
         {
            // selectedOption.setBackgroundResource(R.drawable.background_color);
             //selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
             score++;
         }else{
             //incorrect
            //selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctbtn=(Button) optionContainer.findViewWithTag(list.get(postion).getCoorectAnswer());
           // correctbtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableoption(boolean enable){
        for(int i=0;i<4;i++) {
            optionContainer.getChildAt(i).setEnabled(enable);
            if(enable){
               //optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }

        }
    }

    }