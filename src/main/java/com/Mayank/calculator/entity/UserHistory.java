package com.Mayank.calculator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "userhistory")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private String result;
    public UserHistory(){

    }
    public UserHistory(int id, String expression, String result) {
        this.id = id;
        this.expression = expression;
        this.result = result;
    }

    public UserHistory(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserHistory{" +
                "id=" + id +
                ", expression='" + expression + '\'' +
                ", result=" + result +
                '}';
    }
}
