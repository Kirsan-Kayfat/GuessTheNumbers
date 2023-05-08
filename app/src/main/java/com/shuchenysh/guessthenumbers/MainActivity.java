package com.shuchenysh.guessthenumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shuchenysh.guessthenumbers.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static int score = 0;
    private static int firstNumber;
    private static int secondNumber;
    private static int answer;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        score = Integer.parseInt(binding.textViewScoreNumber.getText().toString());
        example();

        binding.buttonEnterAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer();
            }
        });
    }

    private void clickAnswer() {
        if (binding.editTextInputAnswer.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this,
                    "Please, input your answer",
                    Toast.LENGTH_SHORT).show();
        } else {
            answer = Integer.parseInt(binding.editTextInputAnswer.getText().toString());

            if (answer == firstNumber + secondNumber) {
                binding.textViewScorePlus.setVisibility(View.VISIBLE);
                binding.textViewScoreMinus.setVisibility(View.GONE);
                score++;
                binding.textViewScoreNumber.setText(String.valueOf(score));
                binding.textViewCorrectAnswer.setVisibility(View.VISIBLE);
                binding.textViewInCorrectAnswer.setVisibility(View.GONE);
                example();
            } else {
                binding.textViewScoreMinus.setVisibility(View.VISIBLE);
                binding.textViewScorePlus.setVisibility(View.GONE);
                score--;
                binding.textViewScoreNumber.setText(String.valueOf(score));
                binding.textViewCorrectAnswer.setVisibility(View.GONE);
                binding.textViewInCorrectAnswer.setVisibility(View.VISIBLE);
                example();
            }

            if (score > 3) {
                binding.textViewScoreNumber.setTextColor(getColor(android.R.color.holo_green_light));
            } else {
                binding.textViewScoreNumber.setTextColor(getColor(android.R.color.holo_red_light));
            }
        }
        binding.editTextInputAnswer.setText("");

        if (score == 0) {
            Intent intent = SecondScreen.newIntent(this);
            intent.putExtra("score", "You Lost");
            startActivity(intent);
            score = 5;
            binding.textViewScoreNumber.setText(String.valueOf(score));
            binding.textViewCorrectAnswer.setVisibility(View.GONE);
            binding.textViewInCorrectAnswer.setVisibility(View.GONE);
            binding.textViewScoreMinus.setVisibility(View.GONE);
            binding.textViewScorePlus.setVisibility(View.GONE);
            binding.textViewScoreNumber.setTextColor(getColor(android.R.color.holo_green_light));

        }
        if (score == 10) {
            Intent intent = SecondScreen.newIntent(this);
            intent.putExtra("score", "You Won");
            startActivity(intent);
            score = 5;
            binding.textViewScoreNumber.setText(String.valueOf(score));
            binding.textViewCorrectAnswer.setVisibility(View.GONE);
            binding.textViewInCorrectAnswer.setVisibility(View.GONE);
            binding.textViewScoreMinus.setVisibility(View.GONE);
            binding.textViewScorePlus.setVisibility(View.GONE);
            binding.textViewScoreNumber.setTextColor(getColor(android.R.color.holo_green_light));

        }
    }

    private void example() {
        firstNumber = random.nextInt(99) + 1;
        secondNumber = random.nextInt(99) + 1;
        binding.textViewFirstNumber.setText(String.valueOf(firstNumber));
        binding.textViewSecondNumber.setText(String.valueOf(secondNumber));
    }
}