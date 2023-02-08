package com.orhava.trivia2;
public class Score {
    private String username;
    private int score;
    private int place;
    private int index_pic;
    public Score(){}

    public Score(String username, int score, int index_pic) {
        this.username = username;
        this.score = score;
        this.index_pic=index_pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getIndex_pic() {
        return index_pic;
    }
}
