package com.shuchenysh.guessthenumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shuchenysh.guessthenumbers.databinding.ActivityMainBinding;
import com.shuchenysh.guessthenumbers.databinding.ActivitySecondScreenBinding;

public class SecondScreen extends AppCompatActivity {

    private ActivitySecondScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String finish = getIntent().getStringExtra("score");
        binding.textViewFinish.setText(finish);
        if (finish.equals("You Lost")) {
            binding.textViewFinish.setBackgroundColor(getColor(android.R.color.holo_red_light));
        } else {
            binding.textViewFinish.setBackgroundColor(getColor(android.R.color.holo_green_light));
        }

        binding.imageViewCloseScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SecondScreen.class);

    }
}