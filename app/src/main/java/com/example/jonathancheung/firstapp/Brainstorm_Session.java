package com.example.jonathancheung.firstapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Brainstorm_Session extends AppCompatActivity {

    public final static String timer_key = "com.example.jonathancheung.firstapp.timer_key"; //MUST BE UNIQUE!
    public final static String topic_key = "com.example.jonathancheung.firstapp.topic_key";
    TextView textViewTime;
    TextView textViewTopic;
    private ArrayList<String> BrainstormList;
    private ArrayAdapter<String>  BrainAdapter;
    private EditText txtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainstorm__session);

        //Get the timer value from the previous activity
        Intent submit = getIntent();
        String timer_value = submit.getStringExtra(timer_key);
        String topic_value = submit.getStringExtra(topic_key);

        //set Topic string in Brainstorm Session
        textViewTopic = (TextView) findViewById(R.id.header_topic);
        textViewTopic.setText(topic_value);

        //create the timer
        textViewTime = (TextView) findViewById(R.id.timer_text_view);
        textViewTime.setText(timer_value);
        final Counter timer = new Counter (300000, 1000);
        timer.start();

        //create brainstorm item list
        ListView IdeaList = (ListView) findViewById(R.id.current_idealist);

        String [] items= {"Idea1", "Idea2", "Idea3"};
        BrainstormList = new ArrayList<>(Arrays.asList(items));
        BrainAdapter = new ArrayAdapter<String>(this,R.layout.brainstorm_list_item, R.id.brainstorm_item, BrainstormList);
        IdeaList.setAdapter(BrainAdapter);

        txtInput = (EditText) findViewById(R.id.addIdea_EditText);
        Button addButton = (Button) findViewById(R.id.addIdea_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewItem = txtInput.getText().toString();
                BrainstormList.add(NewItem);
                BrainAdapter.notifyDataSetChanged();
                txtInput.setText("");
            }
        });
    }

    public class Counter extends CountDownTimer {

        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%2d:02%d:%2%d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.MILLISECONDS.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toMinutes(millis)));
                System.out.println(hms); //do we need this?????
                textViewTime.setText(hms);
        }

        @Override
        public void onFinish() {
        textViewTime.setText("BRAINSTORM SESSION OVER!");
        }
    }
}
