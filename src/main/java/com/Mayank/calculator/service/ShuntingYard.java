package com.Mayank.calculator.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

class ShuntingYard{
    public boolean isAlphanumeric(char ch){
        if(ch == '.')
            return true;
        else{
            return ch >= 48 && ch <= 57 || ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122;
        }

    }

    public boolean isOperator(char op){
        return op == '~' || op == '#' || op == '+' || op == '-' ||
                op == '*' || op == '/' || op == '[' || op == ']' || op == '^';
    }

    public boolean isUnary(char op){
        return op == '~' || op == '#';
    }

    public int getPrecedence(char operator){
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('#',20);
        precedence.put('~',20);
        precedence.put('+',12);
        precedence.put('-',12);
        precedence.put('*',13);
        precedence.put('/',13);
        precedence.put('^',16);
        precedence.put('[',17);
        precedence.put(']',17);

        return precedence.get(operator);
    }

    public double perform(char operator, String x, String y) {
        double a = Double.parseDouble(x);
        double b = Double.parseDouble(y);


        Map<Character, DoubleBinaryOperator> operation = new HashMap<>();
        operation.put('+', (p, q) -> p + q);
        operation.put('-', (p, q) -> p - q);
        operation.put('*', (p, q) -> p * q);
        operation.put('/', (p, q) -> p / q);
        operation.put('^', (p, q) -> Math.pow(p, q));
        DoubleBinaryOperator func = operation.get(operator);
        return func.applyAsDouble(a, b);
    }

    public double performUnary(char operator, String x){
        double a = Double.parseDouble(x);
        if(operator == '#')
            return a;
        else
            return -a;
    }
    public boolean isBalanced(String infix){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < infix.length(); i++){
            char op = infix.charAt(i);
            if(op == '['){
                stack.push(op);
            }
            else if(op == ']')
                stack.pop();
        }

        return stack.isEmpty();
    }
    public String calculateExpression(String infix){
        String finalResult = "";
        try{

            // checking for balanced infix expression
            if(!isBalanced(infix))
                System.out.println("Enter A valid expression");

            Stack<String> operand = new Stack<>();
            Stack<Character> operator = new Stack<>();
            StringBuilder currentOperand = new StringBuilder("");
            boolean unary = true;

            for (int i = 0; i < infix.length(); i++) {
                char op = infix.charAt(i);

                if(isOperator(op)){
                    if(!currentOperand.isEmpty()){
                        operand.push(currentOperand.toString());
                        currentOperand.setLength(0);
                        unary = false; // once you have an operator , after than only a binary operator can come
                    }

                    if(op == '['){
                        operator.push(op);
                    }
                    else if(op == ']'){
                        while (!(operator.isEmpty()) && !(operator.get(operator.size()-1) == '[')){
                            char a = operator.pop();
                            if(isUnary(a)){
                                String x = operand.pop();
                                double result = performUnary(a, x);
                                operand.push(Double.toString(result));
                            }
                            else{
                                String x = operand.pop();
                                String y = operand.pop();
                                double result = perform(a, y, x);
                                operand.push(Double.toString(result));
                            }

                        }
                        operator.pop();
                    }
                    else {
                        if(unary){
                            if(op == '-')
                                op = '~';
                            else if(op == '+')
                                op = '#';
                        }

                        while (!(operator.isEmpty()) && getPrecedence(operator.get(operator.size() - 1)) >= getPrecedence(op)
                                && !(operator.get(operator.size() - 1) == '[')){
                            char a = operator.pop();

                            if(isUnary(a)){
                                String x = operand.pop();
                                double result = performUnary(a, x);
                                operand.push(Double.toString(result));
                            }
                            else{
                                String x = operand.pop();
                                String y = operand.pop();

                                double result = perform(a, y, x);
                                operand.push(Double.toString(result));
                            }
                        }

                        operator.push(op);
                        unary = true; //once the binary operator is pushed , now you can have a unary operator or an operand


                    }
                } else if (isAlphanumeric(op)) {
                    currentOperand.append(op);
                }
            }
            if(!currentOperand.isEmpty())
                operand.push(currentOperand.toString());

            while(!operator.isEmpty()){
                char a = operator.pop();

                if(isUnary(a)){
                    String x = operand.pop();
                    double result = performUnary(a, x);
                    operand.push(Double.toString(result));
                }
                else{
                    String x = operand.pop();
                    String y = operand.pop();

                    double result = perform(a, y, x);
                    operand.push(Double.toString(result));
                }
            }
            finalResult = operand.peek();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return finalResult;
    }
}