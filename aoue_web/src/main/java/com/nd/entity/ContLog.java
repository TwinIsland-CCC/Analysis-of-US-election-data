package com.nd.entity;

//献金实体类

import java.io.Serializable;

public class ContLog implements Serializable {
    private String dt;
    private String state;
    private String candidate;
    private int money;

    @Override
    public String toString() {
        return "ContLog{" +
                "dt='" + dt + '\'' +
                ", state='" + state + '\'' +
                ", candidate='" + candidate + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public ContLog() {
    }

    public ContLog(String dt, String state, String candidate, int money) {
        this.dt = dt;
        this.state = state;
        this.candidate = candidate;
        this.money = money;
    }

    public String getDate() {
        return dt;
    }

    public void setDate(String dt) {
        this.dt = dt;
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
