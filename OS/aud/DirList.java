package aud;
import java.io.*;
import java.util.*;
public class DirList {
    static class DirFilter implements FilenameFilter{
        String afn;
        DirFilter(String afn){
            this.afn = afn;
        }
        public boolean accept(File dir, String name){
            String f = new File(name).getName();
            return f.contains(afn);
        }
    }
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0){
            list = path.list();
        }
        else {
            list = path.list(new DirFilter(args[0]));
        }
        for (int i = 0; i < Objects.requireNonNull(list).length; i++) {
            System.out.println(list[i]);
        }
    }
}
