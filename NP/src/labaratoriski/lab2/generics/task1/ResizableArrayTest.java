package labaratoriski.lab2.generics.task1;

import java.util.*;

class ResizableArray<T>{
    private T[] elements;
    private int size;
    @SuppressWarnings("unchecked")
    ResizableArray(){
        this.elements = (T[]) new Object[10];
        size = 0;
    }
    public void addElement(T Element){
        if(size + 1 > elements.length){
            this.elements = Arrays.copyOf(elements, size + 3);
        }
        this.elements[size++] = Element;
    }

    public boolean removeElement(T Element){
        for(int i = 0; i<size; i++){
            if(Objects.equals(Element, elements[i])){
                for(int j = i; j<size-1; j++){
                    elements[j] = elements[j+1];
                }
                elements[--size] = null;
                int blanks = elements.length - size;
                if(blanks > 3) elements = Arrays.copyOf(elements, elements.length-blanks+1);
                return true;
            }
        }
        return false;
    }
    public boolean contains(T Element){
        for (T el : elements) {
            if (Objects.equals(el,Element)) return true;
        }
        return false;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int count(){
        return size;
    }
    public T elementAt(int idx){
        if (idx < 0 || idx >= size) throw new ArrayIndexOutOfBoundsException(idx);
        return elements[idx];
    }
    public Object[] toArray(){
        return elements;
    }
    @SuppressWarnings("unchecked")
    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? super T> src){
        int n = src.count();
        for(int i = 0; i<n; i++){
            dest.addElement((T) src.elementAt(i));
        }
    }
}
class IntegerArray extends ResizableArray<Integer>{
    IntegerArray(){
        super();
    }
    public double sum(){
        double total = 0;
        for(int i = 0; i<this.count(); i++) {
            total += this.elementAt(i);
        }
        return total;
    }
    public double mean(){
        return this.sum() / this.count();
    }

    public int countNonZero(){
        int total = 0;
        for (int i = 0; i<this.count(); i++){
            if(this.elementAt(i) != 0) total++;
        }
        return total;
    }
    public IntegerArray distinct(){
        IntegerArray dis = new IntegerArray();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i<this.count(); i++){
            Integer num = this.elementAt(i);
            if(!set.contains(num)) {
                dis.addElement(num);
                set.add(num);
            }
        }
        return dis;
    }
    public IntegerArray increment(int offset){
        IntegerArray inc = new IntegerArray();
        for (int i = 0; i<this.count(); i++){
            inc.addElement(this.elementAt(i) + offset);
        }
        return inc;
    }
}

public class ResizableArrayTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if ( test == 0 ) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while ( jin.hasNextInt() ) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if ( test == 1 ) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for ( int i = 0 ; i < 4 ; ++i ) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if ( test == 2 ) { //test IntegerArray
            IntegerArray a = new IntegerArray();
            System.out.println(a.isEmpty());
            while ( jin.hasNextInt() ) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if ( a.sum() > 100 )
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if ( test == 3 ) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for ( int w = 0 ; w < 500 ; ++w ) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k =  2000;
                int t =  1000;
                for ( int i = 0 ; i < k ; ++i ) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for ( int i = 0 ; i < t ; ++i ) {
                    a.removeElement(k-i-1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }

}
