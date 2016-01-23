package com.m101j.hw31;

public class Score {
    private String type;
    private Double score;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
