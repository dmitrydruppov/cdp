/*
package com.epam.druppov.cdp.calculator;

import groovyjarjarantlr.StringUtils;

import java.util.*;

*/
/**
 * Created by Dmytro_Druppov on 10/24/2016.
 *//*

public class GroovyCalculator implements Calculator {


    public int priority(char operator) {
        switch (operator) {
            case '*':
            case '/':
                return 3;
            case '-':
            case '+':
                return 2;
            case '(':
                return 1;
            default:
                throw new IllegalArgumentException();
        }
    }

    boolean isBigger(char ch, Stack<Character> stack) {
        for(char var : stack) {
            if(priority(var) > priority(ch) )
                return false;
        }
        return true;
    }
    private static String operators = "+-*//*
";
    private static String delimiters = "() " + operators;
    public static boolean flag = true;
    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) return true;
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isFunction(String token) {
        if (token.equals("sqrt") || token.equals("cube") || token.equals("pow10")) return true;
        return false;
    }

    private static int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*") || token.equals("/")) return 3;
        return 4;
    }



    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<String>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr = "";
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                System.out.println("Некорректное выражение.");
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) continue;
            if (isFunction(curr)) stack.push(curr);
            else if (isDelimiter(curr)) {
                if (curr.equals("(")) stack.push(curr);
                else if (curr.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            System.out.println("Скобки не согласованы.");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        postfix.add(stack.pop());
                    }
                }
                else {
                    if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev)  && !prev.equals(")")))) {
                        // унарный минус
                        curr = "u-";
                    }
                    else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(curr);
                }
            }
            else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                System.out.println("Скобки не согласованы.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    private boolean isNumeric(String line) {
        char[] charArray = line.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(!Character.isDigit(charArray[i])) {
                return false;
            }
        }
        return true;
    }

    public double calc(ArrayList<String> output) {
        Deque<Double> stack = new ArrayDeque<Double>();
        double n1, n2, res = 0;

        for(int i = 0; i < output.size(); i++) {
            if (isNumeric(output.get(i))) {
                stack.push(Double.valueOf(output.get(i)));
            } else {
                switch (output.get(i).charAt(0)) {
                    case '+': res = stack.pop() + stack.pop(); break;
                    case '-': {
                        Double b = stack.pop(), a = stack.pop();
                        res = a - b;
                        break;
                    }
                    case '*': res = stack.pop() * stack.pop(); break;
                    case '/': {
                        Double b = stack.pop();
                        Double a = stack.pop();
                        res = a / b;
                        break;
                    }
                    default: throw new IllegalArgumentException();
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }

    public static Double calc(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<Double>();
        for (String x : postfix) {
//            if (x.equals("sqrt")) stack.push(Math.sqrt(stack.pop()));
//            else if (x.equals("cube")) {
//                Double tmp = stack.pop();
//                stack.push(tmp * tmp * tmp);
//            }
//            else if (x.equals("pow10")) stack.push(Math.pow(10, stack.pop()));
            */
/*else*//*
 if (x.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (x.equals("-")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a - b);
            }
            else if (x.equals("*")) stack.push(stack.pop() * stack.pop());
            else if (x.equals("/")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a / b);
            }
            //else if (x.equals("u-")) stack.push(-stack.pop());
            else stack.push(Double.valueOf(x));
        }
        return stack.pop();
    }




    public void calculate(String statement) {

        Stack<Character> stack = new Stack<Character>();
        ArrayList<String> output = new ArrayList();
        char[] symbols = statement.toCharArray();
        for(int i = 0; i < symbols.length; i++) {

            if(Character.isDigit(symbols[i])) {
                String operand = "";
                while (i < symbols.length && Character.isDigit(symbols[i])) {
                    operand += symbols[i++];
                }
                --i;
                output.add(operand);
            }
            else if(symbols[i] == '+' || symbols[i] == '-' || symbols[i] == '*' || symbols[i] == '/') {
                while(!stack.isEmpty() && priority(stack.peek()) >= priority(symbols[i])) {
                    output.add(stack.pop() + "");
                }
                stack.push(symbols[i]);
            }
            else if(symbols[i] == '(') {
                stack.push(symbols[i]);
            }
            else if (symbols[i] == ')') {
                while ((stack.peek()) != '(') {
                    output.add(stack.pop() + "");
                }
                stack.pop();
            }
        }
        while(!stack.isEmpty()) {
            output.add(stack.pop() + "");
        }
        System.out.println(output);
        System.out.println(calc(output));
    }

    public static void main(String[] args) {
        GroovyCalculator groovyCalculator = new GroovyCalculator();

        groovyCalculator.calculate("2+3+3");
        System.out.println(GroovyCalculator.parse("2+3+3"));
        System.out.println(calc(GroovyCalculator.parse("2+3+3")));
    }
}
*/
