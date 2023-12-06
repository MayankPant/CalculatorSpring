package com.Mayank.calculator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "userhistory")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private int result;
    public UserHistory(){

    }
    public UserHistory(int id, String expression, int result) {
        this.id = id;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
