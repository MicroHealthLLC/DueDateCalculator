package com.example.maxmoons.duedatecalculator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    public int day = 4;
    // AU this variable is never used public int days = 30;
    //public int [] month = { 1, 2 , 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}; // Could also declare the month as an array
    // and pass it over as a parameter below, both ways work but this is an option.
    public int month = 7;


    // AU: default year has been updated to 2019
    public int year = 19;
    public boolean holder = false;
    public boolean euFormat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar DayBar = (SeekBar) findViewById(R.id.DayBar);
        final SeekBar MonthBar = (SeekBar) findViewById(R.id.MonthBar);
        final SeekBar YearBar = (SeekBar) findViewById(R.id.YearBar);
        // AU: these varibles are never used by the code so they have been commented out and can be deleted to run the app
       /* final TextView DateMenstrual = (TextView) findViewById(R.id.DateMenstrual);
        final Button Calculate = (Button) findViewById(R.id.button);
        final TextView textview = (TextView) findViewById(R.id.textView);
        final TextView textview2 = (TextView) findViewById(R.id.textView2);
        final TextView textview3 = (TextView) findViewById(R.id.textView3);*/
        final CheckBox EUFormat = (CheckBox) findViewById(R.id.EUFormat);

        EUFormat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                euFormat = b;

                DatechangeM();
            }
        });

        DayBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar MHeightInches, int progress, boolean fromUser) {
                day = progress;
                if(day==0)
                    day++; // AU: better way of incrementing the day , simplify code
                DatechangeM();
            }

            public void onStartTrackingTouch(SeekBar MHeightInches) {
            }

            public void onStopTrackingTouch(SeekBar MHeightInches) {
            }
        });


        MonthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar MHeightInches, int progress, boolean fromUser) {
                if(month==0)
                     // AU better way of incrementing  progress
                    progress ++;
                month = progress;

                if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
                    DayBar.setMax(31);
                    else if(month!=2)
                    DayBar.setMax(30);
                else if(year%4==0)
                    DayBar.setMax(29);
                else
                    DayBar.setMax(28);




                DatechangeM();

            }

            public void onStartTrackingTouch(SeekBar MHeightInches) {
            }

            public void onStopTrackingTouch(SeekBar MHeightInches) {
            }
        });

        YearBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar MHeightInches, int progress, boolean fromUser) {
                year = progress;
                if(year==0)
                    year++; // AU: better way to increment
                // AU, calculate for leap years every 4 years.
                if(year%4==0&&month==2)
                    DayBar.setMax(29);
                DatechangeM();
            }

            public void onStartTrackingTouch(SeekBar MHeightInches) {
            }

            public void onStopTrackingTouch(SeekBar MHeightInches) {
            }
        });
    }

    public void DatechangeM()
    {
        final TextView DateMenstrual = (TextView) findViewById(R.id.DateMenstrual);
        if(!euFormat)
            // AU: the text has been changed
            DateMenstrual.setText("The date for your last menstrual period is :\n"+month+"/"+day+"/"+year);
        else
            DateMenstrual.setText(" The date of last menstrual period is:\n"+day+"/"+month+"/"+year);
    }


    public void Calculate(View v)
    {
        holder=!holder;
        int monthHolder=month;
        int dayHolder= day;
        int yearholder = year;
        final SeekBar DayBar = (SeekBar) findViewById(R.id.DayBar);
        final SeekBar MonthBar = (SeekBar) findViewById(R.id.MonthBar);
        final SeekBar YearBar = (SeekBar) findViewById(R.id.YearBar);
        final TextView DateMenstrual = (TextView) findViewById(R.id.DateMenstrual);
        final Button Calculate = (Button) findViewById(R.id.button);
        final TextView textview = (TextView) findViewById(R.id.textView);
        final TextView textview2 = (TextView) findViewById(R.id.textView2);
        final TextView textview3 = (TextView) findViewById(R.id.textView3);
        final TextView textview8 = (TextView) findViewById(R.id.textView8);
        final TextView OvulDate = (TextView) findViewById(R.id.OvulDate);
        final TextView textview11 = (TextView) findViewById(R.id.textView11);
        final CheckBox EUFormat = (CheckBox) findViewById(R.id.EUFormat);
        if(holder)
        {
            DayBar.setVisibility(View.INVISIBLE);
            EUFormat.setVisibility(View.INVISIBLE);
            MonthBar.setVisibility(View.INVISIBLE);
            YearBar.setVisibility(View.INVISIBLE);
            DateMenstrual.setVisibility(View.INVISIBLE);
            // AU: text changed for better user interface
            Calculate.setText("Recalculate");
            textview.setVisibility(View.INVISIBLE);
            textview2.setVisibility(View.INVISIBLE);
            textview3.setVisibility(View.INVISIBLE);
            textview8.setVisibility(View.VISIBLE);
            while((dayHolder+280)>30)
            {
                dayHolder-=30;
                monthHolder+=1;
            }
            if(monthHolder==2&&(dayHolder+280)>28)
            {monthHolder+=1; dayHolder-=28;}
            if(monthHolder>=13)
            {yearholder+=1; monthHolder-=12;}
            if(!euFormat)
                OvulDate.setText(monthHolder+"/"+(dayHolder+280)+"/"+yearholder);
            else
                OvulDate.setText((dayHolder+280)+"/"+monthHolder+"/"+yearholder);
            OvulDate.setVisibility(View.VISIBLE);
            textview11.setVisibility(View.VISIBLE);
        }
        else
        {
            DayBar.setVisibility(View.VISIBLE);
            EUFormat.setVisibility(View.VISIBLE);
            MonthBar.setVisibility(View.VISIBLE);
            YearBar.setVisibility(View.VISIBLE);
            DateMenstrual.setVisibility(View.VISIBLE);
            // AU: text changed for user simplification
            Calculate.setText("Calculate your next menstrual date! ");
            textview.setVisibility(View.VISIBLE);
            textview2.setVisibility(View.VISIBLE);
            textview3.setVisibility(View.VISIBLE);
            textview8.setVisibility(View.INVISIBLE);
            OvulDate.setVisibility(View.INVISIBLE);
            textview11.setVisibility(View.INVISIBLE);

        }
    }

    public void Terms(View v)
    {
        Uri uri = Uri.parse("https://microhealthllc.com/about/terms-of-use/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
