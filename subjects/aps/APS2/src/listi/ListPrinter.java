package listi;

import java.util.List;

public class ListPrinter {
    public static void printStringList(List<String> list){
        boolean first = true;
        for (String nodes : list){
            if (first){
                System.out.print(nodes.toString());
                first = false;
            }else {
                System.out.print("->" + nodes.toString());
            }
        }
        System.out.println();
    }
    public static void printIntegerList(List<String> list){
        boolean first = true;
        for (String nodes : list){
            if (first){
                System.out.print(nodes);
                first = false;
            }else {
                System.out.print("->" + nodes);
            }
        }
        System.out.println();
    }
}
