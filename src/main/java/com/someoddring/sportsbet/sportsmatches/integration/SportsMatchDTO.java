package com.someoddring.sportsbet.sportsmatches.integration;

import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;

public class SportsMatchDTO {
    private String name;
    private String type;
    private double win;
    private double draw;
    private double lose;

    public SportsMatchDTO() {
    }

    public SportsMatchDTO(SportsMatchEntity entity) {
        this.win = entity.getWin();
        this.name = entity.getName();
        this.type = entity.getType();
        this.lose = entity.getLose();
        this.draw = entity.getDraw();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportsMatchDTO that = (SportsMatchDTO) o;

        if (Double.compare(that.draw, draw) != 0) return false;
        if (Double.compare(that.lose, lose) != 0) return false;
        if (Double.compare(that.win, win) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(win);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(draw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lose);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SportsMatchDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", win=" + win +
                ", draw=" + draw +
                ", lose=" + lose +
                '}';
    }
}
