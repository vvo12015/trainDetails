package net.vrakin;

import net.vrakin.service.OrderGeneratorImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean b = true;
        while (b) {
            int value = scanner.nextInt();

            System.out.println(OrderGeneratorImpl.getRandomInteger(value));
            System.out.println("For exit enter 1000");

            if (value == 1000) b = false;
        }
    }
}
