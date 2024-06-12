package com.example.surveysapp.Model;

public class SurveyAnswers {
     private String fullNames;
    private String email;
    private String dateOfBirth;
    private String contactNumber;
    private boolean favoriteFoodPizza;
    private boolean favoriteFoodPasta;
    private boolean favoriteFoodPapAndWors;
    private boolean favoriteFoodOther;
    private int ratingMovies;
    private int ratingRadio;
    private int ratingEatOut;
    private int ratingTV;

    public SurveyAnswers() {
    }

    public SurveyAnswers(String fullNames, String email, String dateOfBirth, String contactNumber, boolean favoriteFoodPizza, boolean favoriteFoodPasta, boolean favoriteFoodPapAndWors, boolean favoriteFoodOther, int ratingMovies, int ratingRadio, int ratingEatOut, int ratingTV) {
        this.fullNames = fullNames;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.favoriteFoodPizza = favoriteFoodPizza;
        this.favoriteFoodPasta = favoriteFoodPasta;
        this.favoriteFoodPapAndWors = favoriteFoodPapAndWors;
        this.favoriteFoodOther = favoriteFoodOther;
        this.ratingMovies = ratingMovies;
        this.ratingRadio = ratingRadio;
        this.ratingEatOut = ratingEatOut;
        this.ratingTV = ratingTV;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isFavoriteFoodPizza() {
        return favoriteFoodPizza;
    }

    public void setFavoriteFoodPizza(boolean favoriteFoodPizza) {
        this.favoriteFoodPizza = favoriteFoodPizza;
    }

    public boolean isFavoriteFoodPasta() {
        return favoriteFoodPasta;
    }

    public void setFavoriteFoodPasta(boolean favoriteFoodPasta) {
        this.favoriteFoodPasta = favoriteFoodPasta;
    }

    public boolean isFavoriteFoodPapAndWors() {
        return favoriteFoodPapAndWors;
    }

    public void setFavoriteFoodPapAndWors(boolean favoriteFoodPapAndWors) {
        this.favoriteFoodPapAndWors = favoriteFoodPapAndWors;
    }

    public boolean isFavoriteFoodOther() {
        return favoriteFoodOther;
    }

    public void setFavoriteFoodOther(boolean favoriteFoodOther) {
        this.favoriteFoodOther = favoriteFoodOther;
    }

    public int getRatingMovies() {
        return ratingMovies;
    }

    public void setRatingMovies(int ratingMovies) {
        this.ratingMovies = ratingMovies;
    }

    public int getRatingRadio() {
        return ratingRadio;
    }

    public void setRatingRadio(int ratingRadio) {
        this.ratingRadio = ratingRadio;
    }

    public int getRatingEatOut() {
        return ratingEatOut;
    }

    public void setRatingEatOut(int ratingEatOut) {
        this.ratingEatOut = ratingEatOut;
    }

    public int getRatingTV() {
        return ratingTV;
    }

    public void setRatingTV(int ratingTV) {
        this.ratingTV = ratingTV;
    }
}
