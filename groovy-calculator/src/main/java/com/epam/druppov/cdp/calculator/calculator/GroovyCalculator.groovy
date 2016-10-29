package com.epam.druppov.cdp.calculator.calculator
/**
 * Created by Dmytro_Druppov on 10/24/2016.
 */
    public class GroovyCalculator implements Calculator {

        private double lastResult;

        def priority = { operator ->
            switch (operator) {
                case '^':
                    return 4;
                case '*':
                case '/':
                    return 3;
                case '-':
                case '+':
                    return 2;
                case '(':
                    return 1;
            }
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
            def stack = new ArrayDeque<Double>();
            for (int i = 0; i < output.size(); i++) {
                if (isNumeric(output.get(i)) || (output.get(i).charAt(0) == '-' && output.get(i).length() > 1 )) {
                    stack.push(Double.valueOf(output.get(i)));
                } else {
                    switch (output.get(i).charAt(0)) {
                        case '^':
                            Double b = stack.pop();
                            Double a = stack.pop();
                            stack.push(a ** b);
                            break;
                        case '+':
                            stack.push(stack.pop() + stack.pop());
                            break;
                        case '-':
                            Double b = stack.pop(), a = stack.pop();
                            stack.push(a - b);
                            break;
                        case '*':
                            stack.push(stack.pop() * stack.pop());
                            break;
                        case '/':
                            Double b = stack.pop();
                            Double a = stack.pop();
                            stack.push(a / b);
                            break;
                    }
                }
            }
            return stack.pop();
        }

        public double calculate(String statement) {
            Stack<Character> stack = new Stack<Character>();
            ArrayList<String> output = new ArrayList();
            char[] symbols = statement?.toCharArray();
            if(symbols == null) {
                return 0;
            }
            for(int i = 0; i < symbols.length; i++) {

                if(Character.isDigit(symbols[i]) || (symbols[i] == '-' && i == 0)) {
                    String operand = symbols[i];
                    while (i+1 < symbols.length && Character.isDigit(symbols[i+1])) {
                        operand += symbols[++i];
                    }
                    //--i;
                    output.add("${operand}");
                }
                else if(symbols[i] == '+' || symbols[i] == '-' || symbols[i] == '*' || symbols[i] == '/' || symbols[i] == '^') {
                    while(!stack.isEmpty() && priority(stack.peek()) >= priority(symbols[i])) {
                        output.add("${stack.pop()}");
                    }
                    stack.push(symbols[i]);
                }
                else if(symbols[i] == '(') {
                    stack.push(symbols[i]);
                }
                else if (symbols[i] == ')') {
                    while ((stack.peek()) != '(') {
                        output.add("${stack.pop()}");
                    }
                    stack.pop();
                }
            }
            while(!stack.isEmpty()) {
                output.add("${stack.pop()}");
            }
            System.out.println(output.toString());
            lastResult = calc(output);
            System.out.println(lastResult);
            return lastResult;
        }

        public double plus(GroovyCalculator other) {
            return this.lastResult + other.lastResult;
        }

}
