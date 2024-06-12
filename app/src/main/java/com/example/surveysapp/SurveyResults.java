package com.example.surveysapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.surveysapp.Model.SurveyAnswers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SurveyResults extends AppCompatActivity {
    private static final String TAG = "SurveyResultsActivity";

    // Firebase
    private DatabaseReference databaseReference;

    // Views
    private TextView totalSurveysTextView;
    private TextView averageAgeTextView;
    private TextView oldestAgeTextView;
    private TextView youngestAgeTextView;
    private TextView percentagePizzaTextView;
    private TextView percentagePastaTextView;
    private TextView percentagePapAndWorsTextView;
    private TextView averageMovieRatingTextView;
    private TextView averageRadioRatingTextView;
    private TextView averageEatOutRatingTextView;
    private TextView averageTvRatingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_survey_results);


        databaseReference = FirebaseDatabase.getInstance().getReference("surveys");

        // Initialize Views
        totalSurveysTextView = findViewById(R.id.total_surveys);
        averageAgeTextView = findViewById(R.id.average_age);
        oldestAgeTextView = findViewById(R.id.oldest_age);
        youngestAgeTextView = findViewById(R.id.youngest_age);
        percentagePizzaTextView = findViewById(R.id.percentage_pizza);
        percentagePastaTextView = findViewById(R.id.percentage_pasta);
        percentagePapAndWorsTextView = findViewById(R.id.percentage_pap_and_wors);
        averageMovieRatingTextView = findViewById(R.id.rating_movies);
        averageRadioRatingTextView = findViewById(R.id.rating_radio);
        averageEatOutRatingTextView = findViewById(R.id.rating_eat_out);
        averageTvRatingTextView = findViewById(R.id.rating_tv);

        // Retrieve survey responses from Firebase Realtime Database
        retrieveSurveyResponses();
    }

    private void retrieveSurveyResponses() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<SurveyAnswers> surveyResponses = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SurveyAnswers surveyAnswer = snapshot.getValue(SurveyAnswers.class);
                    surveyResponses.add(surveyAnswer);
                }
                calculateStatistics(surveyResponses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error getting survey responses", databaseError.toException());
            }
        });
    }

    private void calculateStatistics(List<SurveyAnswers> surveyResponses) {
        int totalSurveys = surveyResponses.size();
        double totalAge = 0;
        int oldestAge = Integer.MIN_VALUE;
        int youngestAge = Integer.MAX_VALUE;
        int pizzaCount = 0;
        int pastaCount = 0;
        int papAndWorsCount = 0;
        int movieRatingsSum = 0;
        int radioRatingsSum = 0;
        int eatOutRatingsSum = 0;
        int tvRatingsSum = 0;

        for (SurveyAnswers response : surveyResponses) {
            String[] dobParts = response.getDateOfBirth().split("-");
            int yearOfBirth = Integer.parseInt(dobParts[0]);
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            int age = currentYear - yearOfBirth;

            totalAge += age;
            if (age > oldestAge) oldestAge = age;
            if (age < youngestAge) youngestAge = age;

            if (response.isFavoriteFoodPizza()) {
                pizzaCount++;
            }
            if (response.isFavoriteFoodPasta()) {
                pastaCount++;
            }
            if (response.isFavoriteFoodPapAndWors()) {
                papAndWorsCount++;
            }

            movieRatingsSum += response.getRatingMovies();
            radioRatingsSum += response.getRatingRadio();
            eatOutRatingsSum += response.getRatingEatOut();
            tvRatingsSum += response.getRatingTV();
        }

        double averageAge = totalAge / totalSurveys;
        double percentagePizza = (pizzaCount / (double) totalSurveys) * 100;
        double percentagePasta = (pastaCount / (double) totalSurveys) * 100;
        double percentagePapAndWors = (papAndWorsCount / (double) totalSurveys) * 100;
        double averageMovieRating = movieRatingsSum / (double) totalSurveys;
        double averageRadioRating = radioRatingsSum / (double) totalSurveys;
        double averageEatOutRating = eatOutRatingsSum / (double) totalSurveys;
        double averageTvRating = tvRatingsSum / (double) totalSurveys;

        // Update UI with the calculated statistics
        int finalOldestAge = oldestAge;
        int finalYoungestAge = youngestAge;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalSurveysTextView.setText(String.valueOf(totalSurveys));
                averageAgeTextView.setText(String.format(Locale.getDefault(), "%.2f", averageAge));
                oldestAgeTextView.setText(String.valueOf(finalOldestAge));
                youngestAgeTextView.setText(String.valueOf(finalYoungestAge));
                percentagePizzaTextView.setText(String.format(Locale.getDefault(), "%.2f%%", percentagePizza));
                percentagePastaTextView.setText(String.format(Locale.getDefault(), "%.2f%%", percentagePasta));
                percentagePapAndWorsTextView.setText(String.format(Locale.getDefault(), "%.2f%%", percentagePapAndWors));
                averageMovieRatingTextView.setText(String.format(Locale.getDefault(), "%.2f", averageMovieRating));
                averageRadioRatingTextView.setText(String.format(Locale.getDefault(), "%.2f", averageRadioRating));
                averageEatOutRatingTextView.setText(String.format(Locale.getDefault(), "%.2f", averageEatOutRating));
                averageTvRatingTextView.setText(String.format(Locale.getDefault(), "%.2f", averageTvRating));
            }
        });
    }


}