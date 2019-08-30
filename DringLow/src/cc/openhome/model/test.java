package cc.openhome.model;

import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner cin= new Scanner(System.in);
        String s;
        while (cin.hasNext()) {
            s=cin.nextLine();
            System.out.println("hello, " + s);
        }
    }
}