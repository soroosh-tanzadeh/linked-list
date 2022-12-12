package ir.pgu;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> l = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            l.add("" + i);
            l.remove("9");
            l.add("11");
            l.add("12");
            l.addAfter("12", "26");
            l.add("13");
            System.out.println(l.lastNode.getData());
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
