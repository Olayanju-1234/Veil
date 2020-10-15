package com.joe.veil;

import android.content.Intent;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private Button nextButton, prevButton;
    private int currentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);

        viewPager = findViewById(R.id.viewpager);
        dotsLayout = findViewById(R.id.dots);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);

        viewPager.addOnPageChangeListener(viewListener);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    public void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextColor(getResources().getColor(R.color.colorDots));
            dots[i].setTextSize(50);


            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }


    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDots(i);
            currentPage = i;

            if (i == 0) {
                nextButton.setEnabled(true);
                prevButton.setEnabled(false);
                prevButton.setVisibility(View.INVISIBLE);

                nextButton.setText(R.string.next);
                prevButton.setText("");
            } else if (i == dots.length - 1) {
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                prevButton.setVisibility(View.VISIBLE);

                nextButton.setText(R.string.finish);
                prevButton.setText(R.string.back);
            } else {
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                prevButton.setVisibility(View.VISIBLE);

                nextButton.setText(R.string.next);
                prevButton.setText(R.string.back);
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                if (currentPage == dots.length - 1) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    viewPager.setCurrentItem(currentPage + 1);
                }
                break;
            case R.id.prevButton:
                viewPager.setCurrentItem(currentPage - 1);
                break;
        }
    }
}