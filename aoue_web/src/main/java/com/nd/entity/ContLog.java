package com.nd.entity;

//献金实体类

public class ContLog {
    private String date;
    private String state;
    private String candidate;
    private int money;

    @Override
    public String toString() {
        return "ContLog{" +
                "date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", candidate='" + candidate + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public ContLog() {
    }

    public ContLog(String date, String state, String candidate, int money) {
        this.date = date;
        this.state = state;
        this.candidate = candidate;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
