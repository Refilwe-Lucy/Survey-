package com.example.surveysapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surveysapp.Model.SurveyAnswers;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillOut extends AppCompatActivity {
    private EditText fullNames, email, dateOfBirth, contactNumber;
    private CheckBox pizza, pasta, papAndWors, other;
    private RadioGroup ratingMovies, ratingRadio, ratingEatOut, ratingTV;
    private Button submitButton, viewResultsButton;
    private DatabaseReference databaseReference;

    private static final int RATING_MOVIES_1 = R.id.rating_movies_1;
    private static final int RATING_MOVIES_2 = R.id.rating_movies_2;
    private static final int RATING_MOVIES_3 = R.id.rating_movies_3;
    private static final int RATING_MOVIES_4 = R.id.rating_movies_4;
    private static final int RATING_MOVIES_5 = R.id.rating_movies_5;

    private static final int RATING_RADIO_1 = R.id.rating_radio_1;
    private static final int RATING_RADIO_2 = R.id.rating_radio_2;
    private static final int RATING_RADIO_3 = R.id.rating_radio_3;
    private static final int RATING_RADIO_4 = R.id.rating_radio_4;
    private static final int RATING_RADIO_5 = R.id.rating_radio_5;

    private static final int RATING_EAT_OUT_1 = R.id.rating_eat_out_1;
    private static final int RATING_EAT_OUT_2 = R.id.rating_eat_out_2;
    private static final int RATING_EAT_OUT_3 = R.id.rating_eat_out_3;
    private static final int RATING_EAT_OUT_4 = R.id.rating_eat_out_4;
    private static final int RATING_EAT_OUT_5 = R.id.rating_eat_out_5;

    private static final int RATING_TV_1 = R.id.rating_tv_1;
    private static final int RATING_TV_2 = R.id.rating_tv_2;
    private static final int RATING_TV_3 = R.id.rating_tv_3;
    private static final int RATING_TV_4 = R.id.rating_tv_4;
    private static final int RATING_TV_5 = R.id.rating_tv_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fill_out);
        viewResultsButton = findViewById(R.id.view_results_button);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> submitSurvey());

        viewResultsButton.setOnClickListener(v -> {
            Intent intent = new Intent(FillOut.this, SurveyResults.class);
            startActivity(intent);
        });
        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("surveys");

        // Initialize views
        fullNames = findViewById(R.id.full_names);
        email = findViewById(R.id.email);
        dateOfBirth = findViewById(R.id.date_of_birth);
        contactNumber = findViewById(R.id.contact_number);
        pizza = findViewById(R.id.pizza);
        pasta = findViewById(R.id.pasta);
        papAndWors = findViewById(R.id.pap_and_wors);
        other = findViewById(R.id.other);
        ratingMovies = findViewById(R.id.rating_movies);
        ratingRadio = findViewById(R.id.rating_radio);
        ratingEatOut = findViewById(R.id.rating_eat_out);
        ratingTV = findViewById(R.id.rating_tv);
        submitButton = findViewById(R.id.submit_button);

        // Set up the submit button click listener
        submitButton.setOnClickListener(v -> submitSurvey());
    }

    private void submitSurvey() {
        String name = fullNames.getText().toString().trim();
        String emailAddr = email.getText().toString().trim();
        String dob = dateOfBirth.getText().toString().trim();
        String contact = contactNumber.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(emailAddr) || TextUtils.isEmpty(dob) || TextUtils.isEmpty(contact)) {
            Toast.makeText(this, "Please fill in all the personal details.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isPizzaChecked = pizza.isChecked();
        boolean isPastaChecked = pasta.isChecked();
        boolean isPapAndWorsChecked = papAndWors.isChecked();
        boolean isOtherChecked = other.isChecked();

        int moviesRating = getSelectedRating(ratingMovies);
        int radioRating = getSelectedRating(ratingRadio);
        int eatOutRating = getSelectedRating(ratingEatOut);
        int tvRating = getSelectedRating(ratingTV);

        SurveyAnswers surveyAnswer = new SurveyAnswers(name, emailAddr, dob, contact, isPizzaChecked, isPastaChecked, isPapAndWorsChecked, isOtherChecked, moviesRating, radioRating, eatOutRating, tvRating);

        // Save to Firebase
        databaseReference.push().setValue(surveyAnswer)
                .addOnSuccessListener(aVoid -> Toast.makeText(FillOut.this, "Survey submitted successfully.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(FillOut.this, "Failed to submit survey.", Toast.LENGTH_SHORT).show());
    }


    @SuppressLint("NonConstantResourceId")
    private int getSelectedRating(RadioGroup radioGroup) {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == RATING_MOVIES_1 || checkedId == RATING_RADIO_1 || checkedId == RATING_EAT_OUT_1 || checkedId == RATING_TV_1) {
            return 1;
        } else if (checkedId == RATING_MOVIES_2 || checkedId == RATING_RADIO_2 || checkedId == RATING_EAT_OUT_2 || checkedId == RATING_TV_2) {
            return 2;
        } else if (checkedId == RATING_MOVIES_3 || checkedId == RATING_RADIO_3 || checkedId == RATING_EAT_OUT_3 || checkedId == RATING_TV_3) {
            return 3;
        } else if (checkedId == RATING_MOVIES_4 || checkedId == RATING_RADIO_4 || checkedId == RATING_EAT_OUT_4 || checkedId == RATING_TV_4) {
            return 4;
        } else if (checkedId == RATING_MOVIES_5 || checkedId == RATING_RADIO_5 || checkedId == RATING_EAT_OUT_5 || checkedId == RATING_TV_5) {
            return 5;
        } else {
            return 0;
        }
    }
}