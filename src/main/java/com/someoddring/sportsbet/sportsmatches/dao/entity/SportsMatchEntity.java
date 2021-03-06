package com.someoddring.sportsbet.sportsmatches.dao.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class SportsMatchEntity {
    private static final Logger logger = LoggerFactory.getLogger(SportsMatchEntity.class);
    private String id;
    private String name;
    private String type;
    private double win;
    private double draw;
    private double lose;

    public SportsMatchEntity() {
    }

    public SportsMatchEntity(String name, String type, double win, double draw, double lose) {
        this.name = name;
        this.type = type;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDraw() {
        return draw;
    }

    public void setDraw(double draw) {
        this.draw = draw;
    }

    public double getLose() {
        return lose;
    }

    public void setLose(double lose) {
        this.lose = lose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SportsMatchEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", win=" + win +
                ", draw=" + draw +
                ", lose=" + lose +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportsMatchEntity that = (SportsMatchEntity) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static class Builder extends SportsMatchEntity {
        public static final String SPORTS_MATCH_GERMANY_ITALY = "Germany - Italy";
        private final LinkedList<String> matchNames = new LinkedList<>(Arrays.asList("Russia - France",
                SPORTS_MATCH_GERMANY_ITALY, "Afghanistan - Thailand",
                "Malta - Latvia", "Lithuania - Estonia"));

        private Builder() {
        }

        public static double randomCoefficient() {
            return Math.abs((new Random().nextInt(4 * 10 + 1) - 10) / 10.0) + 1.0d;
        }

        public Builder name(String name) {
            super.name = name;
            return this;
        }

        public Builder type(String type) {
            super.type = type;
            return this;
        }

        public Builder win(double win) {
            super.win = win;
            return this;
        }

        public Builder lose(double lose) {
            super.lose = lose;
            return this;
        }

        public Builder draw(double draw) {
            super.draw = draw;
            return this;
        }

        public SportsMatchEntity build() {
            return new SportsMatchEntity(super.name, super.type, super.win, super.draw, super.lose);
        }

        public SportsMatchEntity random() {
            return name(matchNames.pop())
                    .type("1x2")
                    .win(randomCoefficient())
                    .lose(randomCoefficient())
                    .draw(randomCoefficient())
                    .build();
        }

        private String randomSportsMatchName() {
            return matchNames.get(
                    new Random(System.currentTimeMillis())
                            .nextInt(matchNames.size())
            );
        }
    }
}
