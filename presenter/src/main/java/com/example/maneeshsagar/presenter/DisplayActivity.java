package com.example.maneeshsagar.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    public static final String JOKE="joke_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String joke=getIntent().getStringExtra(JOKE);
        TextView textView=findViewById(R.id.textView);
        textView.setText(joke);
    }
}
