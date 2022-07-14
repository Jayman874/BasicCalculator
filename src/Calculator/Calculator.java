package Calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private final static List<String> equation = new ArrayList<>();
    
    public static float add(float num1, float num2){
        return(num1 + num2);
    }

    public static float subtract(float num1, float num2) {return(num1 - num2); }

    public static float multiply(float num1, float num2) {return(num1 * num2); }

    public static float divide(float num1, float num2) {return(num1 / num2); }

    public static float exponential(float num1, float num2) {return (float) Math.pow(num1, num2); }

    public static void addToList(){
        System.out.println("Enter equation you want solved divided by a space between each character:");
        Scanner scan = new Scanner(System.in);
        String grammar = scan.nextLine();
        String[] divide = grammar.split("\\s");
        Collections.addAll(equation, divide);
        scan.close();
        while (equation.size() > 1){
            getEquation(equation);
        }
        System.out.println("Total is: " + equation.get(0));
    }


    public static void equate(int pos, String symbol){
        String back = equation.get(pos - 1);
        String forward = equation.get(pos + 1);
        float num1 = Float.parseFloat(back);
        float num2 = Float.parseFloat(forward);
        equation.remove(pos);
        for (int i = 0; i <= 1; i++){
            equation.remove(pos - 1);
        }
        float num = switch (symbol) {
            case "/" -> divide(num1, num2);
            case "*" -> multiply(num1, num2);
            case "+" -> add(num1, num2);
            case "-" -> subtract(num1, num2);
            case "^" -> exponential(num1, num2);
            default -> 0.0f;
        };
        String newString = String.valueOf(num);
        equation.add(pos - 1, newString);
//        for (String string: equation){
//            System.out.println(string + " " + "(" + equation.indexOf(string) + ")");
//        }
//       System.out.println("Size: " +equation.size());
//       System.out.println("\n");
    }

    public static void getEquation(List<String> equation) {
        List<String> dividedEquation = new ArrayList();
        int startPos = 0;
        int endPos = 0;
        for (int i = 0; i < equation.size(); i++){
            if (equation.get(i).contains("(")){
                startPos = i + 1;
            } else if (equation.get(i).contains(")")){
                endPos = i - 1;
            }
        }

        for (int i = 0; i < equation.size(); i++) {
            if (equation.get(i).equals("^")) {
                equate(i, "^");
                i = i - 1;
            }
        }
        for (int i = 0; i < equation.size(); i++) {
            if (equation.get(i).contains("*") || equation.get(i).contains("/")) {
                if (equation.get(i).contains("/")) {
                    equate(i, "/");
                    i = i - 1;
                } else if (equation.get(i).contains("*")) {
                    equate(i, "*");
                    i = i - 1;
                }
            }
        }
        for (int i = 0; i < equation.size(); i++) {
            if (equation.get(i).contains("+") || equation.get(i).contains("-")) {
                if (equation.get(i).contains("+") && (!equation.get(i).matches(".*\\d.*"))) {
                    equate(i, "+");
                    i = i - 1;
                } else if (equation.get(i).contains("-") && (!equation.get(i).matches(".*\\d.*"))) {
                    equate(i, "-");
                    i = i - 1;
                }
            }
        }
    }

    public static void main(String[] args){
        addToList();
    }
    
}
