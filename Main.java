// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;
import  java.io.IOException;
public class Main {

    static ArrayList<String> RomanDec = new ArrayList<>();
    static ArrayList<String> Roman = new ArrayList<>();
    static String[] romanDecimal ={"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
    static String[] roman ={"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    static boolean Rom = false;


    public static void main(String[] args) {

        RomanDec.addAll(Arrays.asList(romanDecimal));
        Roman.addAll(Arrays.asList(roman));



        Scanner scn = new Scanner(System.in);

        String input = scn. nextLine();
        System.out.println("input:");
        System.out.println(input);
        input.trim();
        String [] data = input.split(" ");

        if(checkRoman(data)){
            Rom= true;
            int k = 0;
            for (String s:data
                 ) {
               data[k]= readRoman(s);
               k++;
            }
        }

        if(datatooshort(data)){
            System.out.println("строка не является математической операцией");
            System.exit(1);
        }

       try{
           if (cheсknumber(data)){
               System.out.println("число > 10");
               System.exit(1);
           }
       } catch (Exception e) {
           System.out.println("число > 10 либо не является  ни числом и ни римским числом ");
           System.exit(1);
       }


        ArrayList<String> dataList = new ArrayList<>(Arrays.asList(data));

        if(!checkdatalength(data)){
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(1);
        }



        operation(dataList);

        System.out.println("Результат:");
        dataList.forEach(System.out::println);

        if (Rom){

            System.out.println("в римской системе: ");
            if(Objects.equals(dataList.get(0), "0")){
                System.out.println("нет Нулевого числа");
                System.exit(1);
            }
            if(Integer.parseInt(dataList.get(0))<0){
                System.out.println(" нет отрицательных чисел");
                System.exit(1);
            }
            String s=romanOf(Integer.parseInt(dataList.get(0)));
            dataList.remove(0);
            dataList.add(0,s);

            dataList.forEach(System.out::println);

        }
        System.exit(1);

    }

    static int plus(int a, int b){
        return a+b;
    }
    static int minus(int a, int b){
        return a-b;
    }
    static int div(int a, int b){
        return a/b;
    }
    static int mult(int a, int b){
        return a*b;
    }
    static boolean cheсknumber(String[] DATA){
        boolean b = false;
        for (int i = 0;i<= DATA.length;i++){
            if(Integer.parseInt(DATA[i])>10) {
                b= true;
                }
            i++;
            }
        return b;
    }
    static void  operation(ArrayList<String> List){
        while (List.contains("*")){
            int signIndex;
            signIndex =List.indexOf("*");
            int value1 = Integer.parseInt(List.get(signIndex-1));
            int value2 = Integer.parseInt(List.get(signIndex+1));
            int result = mult(value1,value2);
            List.remove(signIndex);
            List.remove(signIndex-1);
            List.remove(signIndex-1);
            List.add(signIndex-1, String.valueOf(result));
        }

        while (List.contains("/")){
            int signIndex;
            signIndex =List.indexOf("/");
            int value1 = Integer.parseInt(List.get(signIndex-1));
            int value2 = Integer.parseInt(List.get(signIndex+1));
            int result = div(value1,value2);;
            List.remove(signIndex);
            List.remove(signIndex-1);
            List.remove(signIndex-1);
            List.add(signIndex-1, String.valueOf(result));
        }

        while (List.contains("-")){
            int indexMult;
            indexMult =List.indexOf("-");
            int value1 = Integer.parseInt(List.get(indexMult-1));
            int value2 = Integer.parseInt(List.get(indexMult+1));
            int result = minus(value1,value2);
            List.remove(indexMult);
            List.remove(indexMult-1);
            List.remove(indexMult-1);
            List.add(indexMult-1, String.valueOf(result));
        }
        while (List.contains("+")){
            int indexMult;
            indexMult =List.indexOf("+");
            int value1 = Integer.parseInt(List.get(indexMult-1));
            int value2 = Integer.parseInt(List.get(indexMult+1));
            int result = plus(value1,value2);
            List.remove(indexMult);
            List.remove(indexMult-1);
            List.remove(indexMult-1);
            List.add(indexMult-1, String.valueOf(result));
        }
    }
    static String readRoman(String s){
        String i = "0";
        switch (s){
            case "I": i = "1";
                break;
            case "II": i = "2";
                break;
            case "III": i = "3";
                break;
            case "IV": i = "4";
                break;
            case "V": i = "5";
                break;
            case "VI": i = "6";
                break;
            case "VII": i = "7";
                break;
            case "VIII": i = "8";
                break;
            case "IX": i = "9";
                break;
            case "X": i = "10";
                break;
            default: i = s;
        }
        return i;
    }
    static boolean checkRoman(String[] s){
        boolean b=false;
        for (String string : roman) {
            if (Objects.equals(s[0], string)) {
                b = true;
                break;
            }
        }
        return b;
    }

    static boolean checkdatalength(String[] s){
        return s.length <= 3;
    }
    static boolean datatooshort(String[] s){
        return s.length < 3;
    }

    static String romanOf(int i){
        int t = i/10;
        String s = "y";
        int rest = i-t*10;
        if(t>=1){
            if(rest>0){
                s= RomanDec.get(t-1) + Roman.get(rest-1);
            }
            else {
                s= RomanDec.get(t-1);
            }

        }
        else {
            s=Roman.get(i-1);
        }
        return s;
    }


}
