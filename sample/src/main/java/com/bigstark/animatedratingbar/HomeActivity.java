package com.bigstark.animatedratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bigstark.animatedratingbar.lib.AnimatedRatingBar;

public class HomeActivity extends AppCompatActivity {

    AnimatedRatingBar arb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        arb = (AnimatedRatingBar) findViewById(R.id.arb);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arb.startAnimate();
            }
        });
    }
}
