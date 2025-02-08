package main.java.ednoDimenzionalniPodatocniStrukturi;

import java.util.*;

public class zadaca1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Queue<String> queue = new LinkedList<>();
        boolean valid = true;
        while (true){
            String element = scan.nextLine();
            if (element.equals("x")) break;
            if (!element.startsWith("end")){
                queue.offer(element);
            } else{
                if (!queue.contains(element.substring(3))){
                    valid = false;
                }
                else {
                    queue.remove(element.substring(3));
                }
            }
        }
        if(!queue.isEmpty()) valid = false;
        if (!valid){
            System.out.println("Invalid");
        }
        else {
            System.out.println("Valid");
        }

    }
}