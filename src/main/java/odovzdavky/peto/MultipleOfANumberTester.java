package odovzdavky.peto;

import java.util.Scanner;

public class MultipleOfANumberTester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // zadaj dve cisla
        System.out.print("Enter two numbers: ");
        // cislo1
        int number1 = input.nextInt();

        // cislo2
        int number2 = input.nextInt();

        // cislo1 % cislo2 = 0
        if (number1 % number2 == 0) {
            // cislo1    je delitelne cislom     cislo2      a vysledok delenia je       cislo1 / cislo2
            System.out.println(number1 + " is divisible by the number " + number2 + " and the result of the division is: " + number1 / number2);
        } else {
            // cislo1   nie je delitelne cislom     cislo2
            System.out.println(number1 + " is not divisible by the number " + number2);
        }
    }
}
